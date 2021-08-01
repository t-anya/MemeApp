package com.example.memes.uiControllers

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.memes.R
import com.example.memes.model.MemeResponseData
import com.example.memes.network.ApiHelper
import com.example.memes.network.RetrofitBuilder
import com.example.memes.utils.Resource
import com.example.memes.utils.Status
import com.example.memes.viewModel.MemeViewModel
import com.example.memes.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MemeActivity : AppCompatActivity() {

    lateinit var memeViewModel: MemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupNextBtn()
        setupShareBtn()
        callApi()
    }

    private fun setupViewModel() {
        memeViewModel = ViewModelProviders.of(
            this@MemeActivity, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MemeViewModel::class.java)
    }

    private fun apiObserver(response: LiveData<Resource<MemeResponseData>>) {
        response.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        Log.e("meme", "loading")
                        progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        Log.e("meme", "success")
                        memeViewModel.memeResponseData = it.data
                        populateData(it.data)
                    }
                    Status.ERROR -> {
                        Log.e("meme", "error")
                        showErrorToast()
                    }
                }
            }
        })
    }

    private fun callApi() {
        val response = memeViewModel.getMemes()
        apiObserver(response)
    }

    private fun populateData(data: MemeResponseData?) {
        if (!data?.url.isNullOrBlank()) {
            Glide.with(this).load(data?.url).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    showErrorToast()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

            }).into(ivMeme)
        } else {
            showErrorToast()
        }
    }

    private fun showErrorToast() {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
    }

    private fun setupNextBtn() {
        tvNext.setOnClickListener {
            callApi()
        }
    }

    private fun setupShareBtn() {
        tvShare.setOnClickListener {
            if (!memeViewModel.memeResponseData?.postLink.isNullOrBlank()) {
                share(memeViewModel.memeResponseData?.postLink!!)
            } else {
                //todo
                Toast.makeText(this, "Data not available", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun share(link: String) {
        val msg = "Hey checkout this meme on Reddit $link"
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, msg)
            type = "text/plain"
        }

        val share = Intent.createChooser(intent.apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TITLE, "Share this meme")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }, null)
        startActivity(share)
    }

}
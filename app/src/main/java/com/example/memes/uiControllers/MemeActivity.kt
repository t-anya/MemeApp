package com.example.memes.uiControllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memes.R

class MemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
    }

    private fun setupViewModel() {

    }

}
package com.example.memes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
    }

    private fun setupViewModel() {

    }

}
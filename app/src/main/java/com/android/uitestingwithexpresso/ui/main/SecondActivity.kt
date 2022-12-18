package com.android.uitestingwithexpresso.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.android.uitestingwithexpresso.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val buttonBack: Button = findViewById(R.id.button_back)
        buttonBack.setOnClickListener {
            onBackPressed()
        }
    }
}
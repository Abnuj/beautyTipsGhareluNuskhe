package com.abnuj.acharybaalkrishnagharelunuskhe

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.webkit.WebView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class dummy : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)
        val tv = findViewById<TextView>(R.id.tv)

    }
}
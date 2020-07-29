package com.tools.jackofall

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        findViewById<Button>(R.id.login).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
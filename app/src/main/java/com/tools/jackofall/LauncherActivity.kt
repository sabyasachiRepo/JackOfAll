package com.tools.jackofall

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val launcherViewModel = ViewModelProviders.of(this).get(LauncherViewModel::class.java)
        when (launcherViewModel.launchType) {
            LaunchType.MAIN_SCREEN -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            LaunchType.ON_BOARDING -> {
                startActivity(Intent(this, OnBoardingActivity::class.java))
                finish()
            }

        }
    }
}
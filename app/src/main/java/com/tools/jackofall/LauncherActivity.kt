package com.tools.jackofall

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val launcherViewModel: LauncherViewModel by viewModels()

        when (launcherViewModel.launchType) {
            LaunchType.MAIN_SCREEN -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            LaunchType.ON_BOARDING -> {
                startActivity(Intent(this, SplashScreenActivity::class.java))
                finish()
            }

        }
    }
}
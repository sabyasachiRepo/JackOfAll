package com.tools.jackofall

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tools.core.data.shared.PreferenceStorage
import com.tools.core.data.shared.SharedPrefStorage

class LauncherViewModel(application: Application) : AndroidViewModel(application) {

    private val preference: PreferenceStorage = SharedPrefStorage(application)
    val launchType: LaunchType = when {
        preference.onBoardingCompleted -> {
            LaunchType.MAIN_SCREEN
        }
        else -> {
            LaunchType.ON_BOARDING
        }
    }

}
package com.tools.jackofall

import androidx.lifecycle.ViewModel
import com.tools.core.data.shared.PreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(preference: PreferenceStorage) : ViewModel() {

    val launchType: LaunchType = when {
        preference.onBoardingCompleted -> {
            LaunchType.MAIN_SCREEN
        }
        else -> {
            LaunchType.ON_BOARDING
        }
    }

}
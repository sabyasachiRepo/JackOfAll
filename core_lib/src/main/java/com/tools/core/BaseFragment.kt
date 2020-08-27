package com.tools.core

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : BaseViewModel> : Fragment() {
    abstract val viewModel: T
    fun setTitle(title: String) {
        CoreLib.appBarLib?.setAppBarTitle(title)
    }

    fun setNavigationIcon(drawable: Drawable) {
        CoreLib.appBarLib?.setNavIcon(drawable)
    }

    fun hideNavigationIcon() {
        CoreLib.appBarLib?.hideNavIcon()
    }
}
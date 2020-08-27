package com.tools.core

import android.graphics.drawable.Drawable

interface AppBarLib {
    fun setAppBarTitle(title: String)
    fun setNavIcon(icon: Drawable)
    fun hideNavIcon()
}
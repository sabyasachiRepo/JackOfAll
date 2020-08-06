package com.tools.jackofall

import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.tools.jackofall.di.AppComponent
import com.tools.jackofall.di.DaggerAppComponent

class JackOfAllApplication : SplitCompatApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }


}
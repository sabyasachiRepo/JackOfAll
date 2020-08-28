package com.tools.jackofall

import android.os.Bundle
import com.tools.core.BaseActivity
import com.tools.jackofall.MainFragment.Companion.newInstance


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(newInstance(), false)
    }

    override fun getFragmentContainerId() = R.id.container



}
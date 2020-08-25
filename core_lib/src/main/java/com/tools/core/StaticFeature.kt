package com.tools.core

import android.view.View
import android.view.ViewGroup

interface StaticFeature {
    fun getFeatureEntryPoint(parent: ViewGroup?): View?
}
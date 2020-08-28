package com.tools.core

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    lateinit var viewModel: VM
    lateinit var binding: T


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false)

        viewModel = ViewModelProviders.of(this, getFactory()).get(getViewModel())
        return binding.root
    }

    abstract fun getFactory(): ViewModelProvider.Factory

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentLayout(): Int


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
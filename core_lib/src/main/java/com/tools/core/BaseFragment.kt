package com.tools.core

import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
        setToolBar()
        return binding.root
    }

    private fun setToolBar() {
        getToolBar()?.apply {
            if (activity is AppCompatActivity) (activity as AppCompatActivity).setSupportActionBar(this)
            setNavigationOnClickListener {
                onBackClick()
            }
        }

    }

    fun onBackClick() {
        activity?.apply {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                onBackPressed()
            }
        }
    }

    abstract fun getFactory(): ViewModelProvider.Factory

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentLayout(): Int

    abstract fun getToolBar(): Toolbar?


    fun setTitle(title: String) {
        if (activity is AppCompatActivity) (activity as AppCompatActivity).supportActionBar?.title = title
    }

    fun setNavigationIcon(drawable: Drawable) {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(drawable)

            }
        }

    }

    fun hideNavigationIcon() {
        if (activity is AppCompatActivity) (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun showErrorAlertMessage(title: String = "Error!", message: String = "Error while getting data") {
        activity?.let {
            AlertDialog.Builder(it).apply {
                setTitle(title)
                setMessage(message)
                setPositiveButton("Ok", DialogInterface.OnClickListener { view, _ -> view.dismiss() })
                show()
            }

        }

    }

}
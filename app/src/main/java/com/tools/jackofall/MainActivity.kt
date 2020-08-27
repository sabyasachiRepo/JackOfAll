package com.tools.jackofall

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.tools.core.BaseActivity
import com.tools.core.CoreLib
import com.tools.jackofall.MainFragment.Companion.newInstance


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        CoreLib.communicationLib = this
        CoreLib.appBarLib = this
        replaceFragment(newInstance(), false)

    }

    override fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment, null)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun setAppBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun setNavIcon(icon: Drawable) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(icon)
    }

    override fun hideNavIcon() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
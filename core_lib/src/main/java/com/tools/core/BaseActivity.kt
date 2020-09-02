package com.tools.core

import android.content.Context
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.play.core.splitcompat.SplitCompat

abstract class BaseActivity : AppCompatActivity(), CommunicationLib {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }

    override fun onResume() {
        super.onResume()
        CoreLib.communicationLib = this
    }

    /* override fun setContentView(layoutResID: Int) {
         val coordinatorLayout = layoutInflater.inflate(R.layout.activity_base, null)
         val childActivityLayoutContainer = coordinatorLayout.findViewById<FrameLayout>(R.id.child_activity_container)
         setSupportActionBar(coordinatorLayout.findViewById<Toolbar>(R.id.toolbar))
         layoutInflater.inflate(layoutResID, childActivityLayoutContainer, true)
         super.setContentView(coordinatorLayout)
     }*/

    override fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(getFragmentContainerId(), fragment, null)
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


    abstract fun getFragmentContainerId(): Int


}
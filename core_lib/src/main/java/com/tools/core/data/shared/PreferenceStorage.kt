package com.tools.core.data.shared

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferenceStorage {
    var onBoardingCompleted: Boolean
}


class SharedPrefStorage(context: Context) : PreferenceStorage {

    private val pref: Lazy<SharedPreferences> = lazy { context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE) }

    override var onBoardingCompleted: Boolean by BooleanPreference(pref, PREF_ONBOARDING, false)


    companion object {
        const val PREFS_NAME = "iosched"
        const val PREF_ONBOARDING = "pref_onboarding"
    }

}


class BooleanPreference(private val preference: Lazy<SharedPreferences>,
                        private val name: String,
                        private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preference.value.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preference.value.edit { putBoolean(name, value) }
    }


}
package com.tools.core.data.shared

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferenceStorage {
    var onBoardingCompleted: Boolean
    var fromCurrency: String
    var toCurrency: String
    var accessToken:String
    var refreshToken:String
}


class SharedPrefStorage @Inject constructor(@ApplicationContext context: Context) : PreferenceStorage {

    private val pref: Lazy<SharedPreferences> = lazy { context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE) }

    override var onBoardingCompleted: Boolean by BooleanPreference(pref, PREF_ONBOARDING, false)
    override var fromCurrency: String by StringPreference(pref, PREF_FROM_CURRENCY, "")
    override var toCurrency: String by StringPreference(pref, PREF_TO_CURRENCY, "")
    override var accessToken:String by StringPreference(pref,PREF_ACCESS_TOKEN,"")
    override var refreshToken:String by StringPreference(pref,PREF_REFRESH_TOKEN,"")


    companion object {
        const val PREFS_NAME = "iosched"
        const val PREF_ONBOARDING = "pref_onboarding"
        const val PREF_FROM_CURRENCY = "pref_from_currency"
        const val PREF_TO_CURRENCY = "pref_to_currency"
        const val PREF_ACCESS_TOKEN="pref_access_token"
        const val PREF_REFRESH_TOKEN="pref_refresh_token"
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

class StringPreference(private val preference: Lazy<SharedPreferences>,
                       private val name: String,
                       private val defaultValue: String
) : ReadWriteProperty<Any, String?> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return preference.value.getString(name, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preference.value.edit { putString(name, value) }
    }


}
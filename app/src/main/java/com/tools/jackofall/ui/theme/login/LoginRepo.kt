package com.tools.jackofall.ui.theme.login

import com.tools.core.data.shared.PreferenceStorage
import com.tools.core.network.JackOfAllAPI
import com.tools.core.network.request.LoginRequest
import com.tools.core.network.response.LoginResponse
import javax.inject.Inject

class LoginRepo @Inject constructor(private val jackOfAllAPI: JackOfAllAPI,private  val preferenceStorage: PreferenceStorage) {

    suspend fun login(loginRequest: LoginRequest)=jackOfAllAPI.login(loginRequest)

    fun saveAuthTokens(loginResponse: LoginResponse){
        preferenceStorage.accessToken=loginResponse.access_token
        preferenceStorage.refreshToken=loginResponse.refresh_token
    }
}
package com.tools.jackofall.ui.theme.login

import com.tools.core.network.JackOfAllAPI
import com.tools.core.network.request.LoginRequest
import javax.inject.Inject

class LoginRepo @Inject constructor(private val jackOfAllAPI: JackOfAllAPI) {

    suspend fun login(loginRequest: LoginRequest)=jackOfAllAPI.login(loginRequest)
}
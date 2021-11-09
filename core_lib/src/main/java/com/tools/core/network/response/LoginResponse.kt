package com.tools.core.network.response

data class LoginResponse(
    val access_token: String,
    val refresh_token: String
)
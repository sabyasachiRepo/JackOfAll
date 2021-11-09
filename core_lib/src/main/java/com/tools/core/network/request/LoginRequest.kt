package com.tools.core.network.request

data class LoginRequest(
    val password: String,
    val username: String
)
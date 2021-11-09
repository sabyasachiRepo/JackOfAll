package com.tools.core.network

import com.tools.core.network.request.LoginRequest
import com.tools.core.network.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface JackOfAllAPI {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}
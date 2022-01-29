package com.tools.core.network.header

import javax.inject.Inject

class ApiHeadersProvider @Inject constructor() {



    /**
     * Returns both the default headers and the headers that are mandatory for authenticated users.
     */
    fun getAuthenticatedHeaders(accessToken: String): AuthenticatedHeaders =
        AuthenticatedHeaders().apply {
            put(AUTHORIZATION, getBearer(accessToken))
        }



    companion object {
        private const val ACCEPT_LANGUAGE = "Accept-Language"
        private const val USER_AGENT = "User-Agent"
        private const val AUTHORIZATION = "Authorization"
        private const val HEADER_ACCEPT = "Accept"
        private fun getBearer(accessToken: String) = "Bearer $accessToken"
    }
}


open class ApiMainHeaders : HashMap<String, String>()
class AuthenticatedHeaders : ApiMainHeaders()
class PublicHeaders : ApiMainHeaders()
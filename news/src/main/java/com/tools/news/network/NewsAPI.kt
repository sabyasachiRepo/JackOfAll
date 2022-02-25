package com.tools.news.network

import com.tools.core.network.header.AuthenticatedHeaders
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

interface NewsAPI {

    @GET("/other/api/news")
    suspend fun getTopHeadLines(@HeaderMap authenticatedHeaders: AuthenticatedHeaders, @QueryMap queryParams: Map<String, String>): NewsArticle


}
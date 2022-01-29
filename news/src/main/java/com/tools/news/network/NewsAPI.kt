package com.tools.news.network

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getTopHeadLines(@QueryMap queryParams: Map<String, String>): NewsArticle


}
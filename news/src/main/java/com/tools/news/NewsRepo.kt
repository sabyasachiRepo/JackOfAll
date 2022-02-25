package com.tools.news

import com.tools.core.data.shared.PreferenceStorage
import com.tools.core.network.header.ApiHeadersProvider
import com.tools.news.network.NewsAPI
import javax.inject.Inject

class NewsRepo @Inject constructor(private val newsAPI: NewsAPI, private val preferenceStorage: PreferenceStorage, private val apiMainHeadersProvider: ApiHeadersProvider) {
    suspend fun getTopHeadlines() = newsAPI.getTopHeadLines(apiMainHeadersProvider.getAuthenticatedHeaders(preferenceStorage.accessToken),mapOf("country" to "us"))
}
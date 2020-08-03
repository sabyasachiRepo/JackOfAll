package com.tools.news

import com.tools.news.network.NewsAPI
import javax.inject.Inject

class NewsRepo @Inject constructor(private val newsAPI: NewsAPI) {
    suspend fun getTopHeadlines() = newsAPI.getTopHeadLines(mapOf("country" to "us", "apiKey" to "5471b29e9e064a3c9ee60c31a2864391"))
}
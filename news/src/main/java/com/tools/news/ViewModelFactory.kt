package com.tools.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tools.news.detail.NewsDetailViewModel

class ViewModelFactory(private val newsRepo: NewsRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepo) as T
        } else if (modelClass.isAssignableFrom(NewsDetailViewModel::class.java)) {
            return NewsDetailViewModel() as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}
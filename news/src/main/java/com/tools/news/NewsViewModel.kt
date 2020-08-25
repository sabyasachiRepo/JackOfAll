package com.tools.news

import androidx.lifecycle.liveData
import com.tools.core.BaseViewModel
import com.tools.core.network.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsRepo: NewsRepo) : BaseViewModel() {

    fun getTopHeadLines() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = newsRepo.getTopHeadlines()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

    }

}
package com.tools.news.injection

import com.tools.core.network.RetrofitProvider
import com.tools.news.NewsRepo
import com.tools.news.ViewModelFactory
import com.tools.news.network.NewsAPI
import dagger.Module
import dagger.Provides

@Module
class NewsModule {
    @Provides
    fun provideNewsAPI(): NewsAPI {
        return RetrofitProvider.getINSTANCE().newsRetrofit.create(NewsAPI::class.java)
    }

    @Provides
    fun provideViewModelFactory(newsRepo: NewsRepo?): ViewModelFactory {
        return ViewModelFactory(newsRepo!!)
    }
}
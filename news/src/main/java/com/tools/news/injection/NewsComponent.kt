package com.tools.news.injection

import com.tools.news.NewsArticleListFragment
import dagger.Component

@Component(modules = [NewsModule::class])
interface NewsComponent {
    fun inject(newsArticleListFragment: NewsArticleListFragment?)
}
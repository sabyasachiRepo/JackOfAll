package com.tools.news.injection

import com.tools.news.NewsArticleListFragment
import com.tools.news.detail.ArticleDetailFragment
import dagger.Component

@Component(modules = [NewsModule::class])
interface NewsComponent {
    fun inject(newsArticleListFragment: NewsArticleListFragment?)
    fun inject(articleDetailFragment: ArticleDetailFragment?)
}
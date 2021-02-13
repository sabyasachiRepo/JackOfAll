package com.tools.news.injection

import com.tools.jackofall.di.FeatureModuleDependency
import com.tools.news.NewsArticleListFragment
import com.tools.news.detail.ArticleDetailFragment
import dagger.Component

@Component(modules = [NewsModule::class], dependencies = [FeatureModuleDependency::class])
interface NewsComponent {
    @Component.Builder
    interface Builder {
        fun appDependencies(featureModuleDependency: FeatureModuleDependency): Builder
        fun build(): NewsComponent
    }

    fun inject(newsArticleListFragment: NewsArticleListFragment?)
    fun inject(articleDetailFragment: ArticleDetailFragment?)
}
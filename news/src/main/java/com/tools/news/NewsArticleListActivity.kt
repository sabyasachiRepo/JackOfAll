package com.tools.news

import android.os.Bundle
import com.tools.core.BaseActivity

class NewsArticleListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_article_list)
        replaceFragment(NewsArticleListFragment.newInstance(1), false)
    }

    override fun getFragmentContainerId() = R.id.news_container

}
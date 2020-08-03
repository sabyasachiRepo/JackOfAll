package com.tools.news

import android.os.Bundle
import com.tools.core.BaseActivity

class NewsArticleListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_article_list)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.news_container, NewsArticleListFragment.newInstance(1), null)
        fragmentTransaction.commit()
    }
}
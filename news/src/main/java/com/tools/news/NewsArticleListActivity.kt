package com.tools.news

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
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

    override fun replaceFragment(fragment: Fragment?, addToBackStack: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setAppBarTitle(title: String) {
        TODO("Not yet implemented")
    }

    override fun setNavIcon(icon: Drawable) {
        TODO("Not yet implemented")
    }

    override fun hideNavIcon() {
        TODO("Not yet implemented")
    }
}
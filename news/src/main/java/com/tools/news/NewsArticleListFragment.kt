package com.tools.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tools.core.BaseFragment
import com.tools.core.network.Status
import com.tools.news.injection.DaggerNewsComponent
import com.tools.news.network.Article
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class NewsArticleListFragment : BaseFragment<NewsViewModel>() {

    private var columnCount = 1

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override lateinit var viewModel: NewsViewModel

    private lateinit var articleAdapter: MyNewsArticlesRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerNewsComponent.create().inject(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
        getTopHeadLines()
    }

    private fun getTopHeadLines() {
        viewModel.getTopHeadLines().observe(viewLifecycleOwner, Observer {

            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { it -> retrieveArticle(it.articles) }
                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_article_list, container, false)
        articleAdapter = MyNewsArticlesRecyclerViewAdapter(arrayListOf())
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = articleAdapter
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                NewsArticleListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }

    private fun retrieveArticle(articles: List<Article>) {
        articleAdapter.apply {
            articleAdapter.addArticles(articles)
            notifyDataSetChanged()
        }
    }
}
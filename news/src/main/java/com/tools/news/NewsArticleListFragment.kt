package com.tools.news

import android.content.Context
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tools.core.BaseFragment
import com.tools.core.network.Status
import com.tools.news.databinding.FragmentNewsArticleListBinding
import com.tools.news.injection.DaggerNewsComponent
import com.tools.news.network.Article
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class NewsArticleListFragment : BaseFragment<FragmentNewsArticleListBinding, NewsViewModel>() {

    private var columnCount = 1

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


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
        articleAdapter = MyNewsArticlesRecyclerViewAdapter(arrayListOf())
        binding.list.adapter = articleAdapter
        LinearLayoutManager(context)
        getTopHeadLines()

    }

    override fun onResume() {
        super.onResume()
        setToolBar()
    }

    private fun setToolBar() {
        setTitle("Trending News")
        val icon = AppCompatResources.getDrawable(requireContext(), com.tools.jackofall.R.drawable.ic_back)
        icon?.let(::setNavigationIcon)
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

    override fun getFactory() = viewModelFactory

    override fun getViewModel() = NewsViewModel::class.java

    override fun getFragmentLayout() = R.layout.fragment_news_article_list
}
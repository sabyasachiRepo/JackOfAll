package com.tools.news

import android.content.Context
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tools.core.BaseActivity
import com.tools.core.BaseFragment
import com.tools.core.network.Status
import com.tools.jackofall.di.FeatureModuleDependency
import com.tools.news.databinding.FragmentNewsArticleListBinding
import com.tools.news.detail.ArticleDetailFragment
import com.tools.news.injection.DaggerNewsComponent
import com.tools.news.network.Article
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class NewsArticleListFragment : BaseFragment<FragmentNewsArticleListBinding>() {

    private var columnCount = 1

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel: NewsViewModel by viewModels { viewModelFactory }

    private lateinit var articleAdapter: NewsArticlesRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerNewsComponent.builder()
                .appDependencies(
                        EntryPointAccessors.fromApplication(
                                context,
                                FeatureModuleDependency::class.java
                        )
                )
                .build()
                .inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        articleAdapter = NewsArticlesRecyclerViewAdapter(arrayListOf(), this)
        binding.viewModel = viewModel
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            list.adapter = articleAdapter
        }
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
        viewModel.headLines.observe(viewLifecycleOwner, Observer {

            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { it -> showArticles(it.articles) }
                    }
                    Status.ERROR -> {
                        showErrorAlertMessage()
                    }
                    Status.LOADING -> {
                        // not required to handle

                    }
                }
            }
        })
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
                NewsArticleListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }

    private fun showArticles(articles: List<Article>) {
        articleAdapter.apply {
            articleAdapter.addArticles(articles)
            notifyDataSetChanged()
        }
    }



    override fun getFragmentLayout() = R.layout.fragment_news_article_list

    override fun getToolBar() = binding.appbar.toolbar

    fun onArticleClickListener(article: Article) {
        (context as BaseActivity).replaceFragment(ArticleDetailFragment.newInstance(article), true)
    }
}
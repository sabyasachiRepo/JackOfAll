package com.tools.news.detail

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.tools.core.BaseFragment
import com.tools.news.R
import com.tools.news.ViewModelFactory
import com.tools.news.databinding.FragmentArticleDetailBinding
import com.tools.news.injection.DaggerNewsComponent
import com.tools.news.network.Article
import javax.inject.Inject


private const val ARG_ARTICLE_DETAIL = "article detail"

class ArticleDetailFragment : BaseFragment<FragmentArticleDetailBinding, NewsDetailViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var article: Article? = null

    var isWebViewLoading: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = it.getParcelable(ARG_ARTICLE_DETAIL)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (article != null) {
            webViewLoadListener()
            binding.article = article
            binding.fragment = this
        }
    }

    private fun webViewLoadListener() {
        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                isWebViewLoading = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                isWebViewLoading = false
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerNewsComponent.create().inject(this)
    }


    companion object {
        @JvmStatic
        fun newInstance(article: Article) =
                ArticleDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_ARTICLE_DETAIL, article)
                    }
                }
    }

    override fun getFactory() = viewModelFactory

    override fun getViewModel() = NewsDetailViewModel::class.java

    override fun getFragmentLayout() = R.layout.fragment_article_detail

    override fun getToolBar() = null
}
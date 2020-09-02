package com.tools.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tools.news.databinding.ArticleItemBinding
import com.tools.news.network.Article


class MyNewsArticlesRecyclerViewAdapter(
        private val values: ArrayList<Article>)
    : RecyclerView.Adapter<MyNewsArticlesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val articleBinding: ArticleItemBinding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(articleBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.articleItemBinding.article = item

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val articleItemBinding: ArticleItemBinding) : RecyclerView.ViewHolder(articleItemBinding.root)

    fun addArticles(users: List<Article>) {
        this.values.apply {
            clear()
            addAll(users)
        }

    }
}
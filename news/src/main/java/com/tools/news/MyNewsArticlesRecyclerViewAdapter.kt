package com.tools.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tools.news.dummy.DummyContent.DummyItem
import com.tools.news.network.Article

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyNewsArticlesRecyclerViewAdapter(
        private val values: ArrayList<Article>)
    : RecyclerView.Adapter<MyNewsArticlesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        Glide.with(holder.ivBanner.context)
                .load(item.urlToImage)
                .into(holder.ivBanner)
        holder.tvTitle.text = item.title
        holder.tvSource.text = item.source.name
        holder.tvAuthor.text = item.author

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivBanner: ImageView = view.findViewById(R.id.iv_news_banner)
        val tvTitle: TextView = view.findViewById(R.id.title)
        val tvSource: TextView = view.findViewById(R.id.source)
        val tvAuthor: TextView = view.findViewById(R.id.author)


    }

    fun addArticles(users: List<Article>) {
        this.values.apply {
            clear()
            addAll(users)
        }

    }
}
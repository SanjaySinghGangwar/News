package com.trei.news.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trei.news.databinding.NewFeedBinding
import com.trei.news.mViewHolder.NewsViewHolder
import com.trei.news.modelClass.mByCountry.Article


class NewsAdapter(private val context: Context,private val listener: ItemListener) :
    RecyclerView.Adapter<NewsViewHolder>() {

    interface ItemListener {
        fun onClickedArticle(url: String)
        fun onClickedShare(title: String, description: String, urlToImage: String, url: String)
    }


    private val items = ArrayList<Article>()

    fun setItems(items: List<Article>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: NewFeedBinding =
            NewFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(context,binding, listener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

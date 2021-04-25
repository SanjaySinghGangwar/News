package com.theaverageguy.news.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theaverageguy.news.databinding.NewFeedBinding
import com.theaverageguy.news.mViewHolder.NewsViewHolder
import com.theaverageguy.news.modelClass.mByCountry.Article


class NewsAdapter(context: Context,private val listener: ItemListener) :
    RecyclerView.Adapter<NewsViewHolder>() {

    interface ItemListener {
        fun onClickedCharacter(url: String)
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
        return NewsViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

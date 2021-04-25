package com.theaverageguy.news.mViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.theaverageguy.news.R
import com.theaverageguy.news.databinding.NewFeedBinding
import com.theaverageguy.news.modelClass.mByCountry.Article

class NewsViewHolder(private val itemBinding: NewFeedBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {


    fun bind(item: Article) {
        itemBinding.author.text = item.author
        itemBinding.heading.text = item.title
        itemBinding.description.text = item.description
        Picasso.get()
            .load(item.urlToImage)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.drawable.ic_error)
            .into(itemBinding.image)
    }

}
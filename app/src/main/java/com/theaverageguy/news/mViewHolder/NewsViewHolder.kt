package com.theaverageguy.news.mViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.theaverageguy.news.Adapters.NewsAdapter
import com.theaverageguy.news.R
import com.theaverageguy.news.databinding.NewFeedBinding
import com.theaverageguy.news.modelClass.mByCountry.Article

class NewsViewHolder(private val itemBinding: NewFeedBinding, private val listener: NewsAdapter.ItemListener) :
    RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var items: Article

    init {
        itemBinding.root.setOnClickListener(this)
    }



    fun bind(item: Article) {
        this.items = item
        itemBinding.author.text = item.author
        itemBinding.heading.text = item.title
        itemBinding.description.text = item.description
        Picasso.get()
            .load(item.urlToImage)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.drawable.ic_error)
            .into(itemBinding.image)


        /*itemBinding.link.setOnClickListener {
            run {
                taskToPerform(item.url)
            }
        }*/
    }

   /* private fun taskToPerform(url: String) {


        *//*val action = HomeDirections.homeToTenantDetails(userID)
        view?.findNavController()?.navigate(action)*//*

    }*/

    override fun onClick(v: View?) {
        listener.onClickedCharacter(items.url)
    }

}
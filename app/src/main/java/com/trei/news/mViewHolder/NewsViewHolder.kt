package com.trei.news.mViewHolder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.trei.news.Adapters.NewsAdapter
import com.trei.news.R
import com.trei.news.Utils.mUtils.showToast
import com.trei.news.databinding.NewFeedBinding
import com.trei.news.modelClass.mByCountry.Article

class NewsViewHolder(
    private val context: Context,
    private val itemBinding: NewFeedBinding,
    private val listener: NewsAdapter.ItemListener
) :
    RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var items: Article

    init {
        itemBinding.link.setOnClickListener(this)
        itemBinding.shareArticle.setOnClickListener(this)
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
        when (v?.id) {
            R.id.shareArticle -> {
                try {
                    listener.onClickedShare(
                        items.title, items.description, items.urlToImage,
                        items.url
                    )
                } catch (e: Exception) {
                    showToast(context, "Can't share this article")
                }

            }
            R.id.link -> {
                try {
                    listener.onClickedArticle(items.url)
                } catch (e: Exception) {
                    showToast(context, "Can't open this article")
                }

            }
        }

    }

}
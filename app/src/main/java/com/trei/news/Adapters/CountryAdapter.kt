package com.trei.news.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trei.news.databinding.CountryListBinding
import com.trei.news.databinding.NewFeedBinding
import com.trei.news.mViewHolder.CountryCodePickerViewHolder
import com.trei.news.modelClass.CountryModel
import com.trei.news.modelClass.mByCountry.Article

class CountryAdapter (private val context: Context, private val listener: ItemListener) :
    RecyclerView.Adapter<CountryCodePickerViewHolder>() {

    interface ItemListener {
       fun onCountrySelected(code: String)
    }


    private val items = ArrayList<CountryModel>()

    fun setItems(items: List<CountryModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryCodePickerViewHolder {
        val binding: CountryListBinding =
            CountryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryCodePickerViewHolder(context,binding, listener)
    }

    override fun onBindViewHolder(holder: CountryCodePickerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

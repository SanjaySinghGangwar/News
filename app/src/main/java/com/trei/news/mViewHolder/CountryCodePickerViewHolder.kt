package com.trei.news.mViewHolder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.trei.news.Adapters.CountryAdapter
import com.trei.news.R
import com.trei.news.databinding.CountryListBinding
import com.trei.news.modelClass.CountryModel

class CountryCodePickerViewHolder(
    private val context: Context,
    private val bind: CountryListBinding,
    private val listener: CountryAdapter.ItemListener
) :
    RecyclerView.ViewHolder(bind.root), View.OnClickListener {

    private lateinit var data: CountryModel

    init {
        bind.card.setOnClickListener(this)
    }

    fun bind(item: CountryModel) {
        this.data = item
        bind.countryName.text = data.name
        bind.countryCode.text = data.code

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.card -> {
                listener.onCountrySelected(data.code)

            }

        }

    }

}
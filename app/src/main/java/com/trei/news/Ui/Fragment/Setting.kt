package com.trei.news.Ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.trei.news.Adapters.CountryAdapter
import com.trei.news.R
import com.trei.news.Ui.BottomSheet.CountryCodePicker
import com.trei.news.databinding.SettingBinding

class Setting( private val listener: settingListener) : BottomSheetDialogFragment(), View.OnClickListener {

    private var _binding: SettingBinding? = null
    private val bind get() = _binding!!

    interface settingListener{
        fun refreshNews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponents()
    }

    private fun initAllComponents() {
        bind.country.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.country -> {
                dismiss()
                val choseCountry = CountryCodePicker(listener)
                choseCountry.showNow(parentFragmentManager, "SANJAY")
            }
        }
    }

}
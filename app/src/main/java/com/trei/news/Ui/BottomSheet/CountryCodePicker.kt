package com.trei.news.Ui.BottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.trei.news.Adapters.CountryAdapter
import com.trei.news.Ui.Fragment.Setting
import com.trei.news.Utils.AppSharePreference
import com.trei.news.databinding.CountryCodePickerBinding
import com.trei.news.modelClass.CountryModel


class CountryCodePicker( private val listener: Setting.settingListener) : BottomSheetDialogFragment(), CountryAdapter.ItemListener {

    private var _binding: CountryCodePickerBinding? = null
    private val bind get() = _binding!!
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var sharedPreference: AppSharePreference
    private val countryList: ArrayList<CountryModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CountryCodePickerBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intiAllComponents()
        addDataOfCountry()
        setUpRecycler()
    }

    private fun addDataOfCountry() {
        countryList.add(CountryModel("India", "in"))
        countryList.add(CountryModel("United Arab Emirates", "ae"))
        countryList.add(CountryModel("Argentina", "ar"))
        countryList.add(CountryModel("Austria", "at"))
        countryList.add(CountryModel("Australia", "au"))
        countryList.add(CountryModel("Belgium", "be"))
        countryList.add(CountryModel("Bulgaria", "bg"))
        countryList.add(CountryModel("Brazil", "br"))
        countryList.add(CountryModel("Canada", "ca"))
        countryList.add(CountryModel("Switzerland", "ch"))
        countryList.add(CountryModel("China", "cn"))
        countryList.add(CountryModel("Colombia", "co"))
        countryList.add(CountryModel("Cuba", "cu"))
        countryList.add(CountryModel("Czechia", "cz"))
        countryList.add(CountryModel("Germany", "de"))
        countryList.add(CountryModel("Egypt", "eg"))
        countryList.add(CountryModel("France", "fr"))
        countryList.add(CountryModel("United Kingdom of Great Britain and Northern Ireland", "gb"))
        countryList.add(CountryModel("Greece", "gr"))
        countryList.add(CountryModel("Hong Kong", "hk"))
        countryList.add(CountryModel("Hungary", "hu"))
        countryList.add(CountryModel("Ireland", "ie"))
        countryList.add(CountryModel("Israel", "il"))
        countryList.add(CountryModel("Italy", "it"))
        countryList.add(CountryModel("Japan", "jp"))
        countryList.add(CountryModel("Korea", "kr"))
        countryList.add(CountryModel("Lithuania", "lt"))
        countryList.add(CountryModel("Latvia", "lv"))
        countryList.add(CountryModel("Morocco", "ma"))
        countryList.add(CountryModel("Mexico", "mx"))
        countryList.add(CountryModel("Malaysia", "my"))
        countryList.add(CountryModel("Nigeria", "ng"))
        countryList.add(CountryModel("Netherlands", "nl"))
        countryList.add(CountryModel("Norway", "no"))
        countryList.add(CountryModel("New Zealand", "nz"))
        countryList.add(CountryModel("Philippines", "ph"))
        countryList.add(CountryModel("Poland", "pl"))
        countryList.add(CountryModel("Portugal", "pt"))
        countryList.add(CountryModel("Romania", "ro"))
        countryList.add(CountryModel("Serbia", "rs"))
        countryList.add(CountryModel("Russian Federation", "ru"))
        countryList.add(CountryModel("Saudi Arabia", "sa"))
        countryList.add(CountryModel("Sweden", "se"))
        countryList.add(CountryModel("Singapore", "sg"))
        countryList.add(CountryModel("Slovenia", "si"))
        countryList.add(CountryModel("Slovakia", "sk"))
        countryList.add(CountryModel("Thailand", "th"))
        countryList.add(CountryModel("Turkey", "tr"))
        countryList.add(CountryModel("Taiwan", "tw"))
        countryList.add(CountryModel("Ukraine", "ua"))
        countryList.add(CountryModel("United States of America", "us"))
        countryList.add(CountryModel("Venezuela", "ve"))
        countryList.add(CountryModel("South Africa", "za"))
    }

    private fun setUpRecycler() {
        countryAdapter = CountryAdapter(requireContext(), this@CountryCodePicker)

        bind.countryRecycler.adapter = countryAdapter
        countryAdapter.setItems(countryList)
    }

    private fun intiAllComponents() {
        sharedPreference = AppSharePreference(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCountrySelected(code: String) {
        listener.refreshNews()
        dismiss()
        sharedPreference.countryCode = code
    }
}
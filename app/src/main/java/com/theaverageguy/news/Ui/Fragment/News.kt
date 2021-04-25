package com.theaverageguy.news.Ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.theaverageguy.news.Adapters.NewsAdapter
import com.theaverageguy.news.Repository.ResporitaryApis
import com.theaverageguy.news.Utils.mUtils.showToast
import com.theaverageguy.news.databinding.NewsFragmentBinding
import com.theaverageguy.news.mRetrofit.ApiRequests
import com.theaverageguy.news.mRetrofit.RetroClient
import com.theaverageguy.news.mViewModel.NewsViewModel
import com.theaverageguy.news.modelClass.mByCountry.TopHeadlineInCountry

class News : Fragment() {

    companion object {
        fun newInstance() = News()
    }

    private lateinit var viewModel: NewsViewModel
    private lateinit var resporitaryApis: ResporitaryApis
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var _binding: NewsFragmentBinding? = null

    private lateinit var mAdapter: NewsAdapter

    private val bind get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponents()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        mAdapter = NewsAdapter(context?.applicationContext!!)
        bind.recycler.layoutManager = LinearLayoutManager(requireContext())
        bind.recycler.adapter = mAdapter
    }

    private fun initAllComponents() {

        val apiService: ApiRequests = RetroClient.getClient()
        resporitaryApis = ResporitaryApis(apiService)
        viewModel = getViewModel()
        viewModel.NewsByCountry.observe(viewLifecycleOwner, { bindUi(it) })

        linearLayoutManager = LinearLayoutManager(context)
        bind.recycler.layoutManager = linearLayoutManager


    }

    private fun bindUi(data: TopHeadlineInCountry) {
        if (data.status == "ok") {
            mAdapter.setItems(data.articles)
        } else {
            showToast(context?.applicationContext!!, "Something gone haywire !!")
        }
    }

    private fun getViewModel(): NewsViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return NewsViewModel(resporitaryApis) as T
            }
        })[NewsViewModel::class.java]
    }
}
package com.trei.news.Ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trei.news.Adapters.NewsAdapter
import com.trei.news.R
import com.trei.news.Repository.ResporitaryApis
import com.trei.news.Utils.AppSharePreference
import com.trei.news.Utils.mUtils.mLog
import com.trei.news.Utils.mUtils.showToast
import com.trei.news.databinding.NewsFragmentBinding
import com.trei.news.mRetrofit.ApiRequests
import com.trei.news.mRetrofit.RetroClient
import com.trei.news.mViewModel.NewsViewModel
import com.trei.news.modelClass.mByCountry.TopHeadlineInCountry
import com.yuyakaido.android.cardstackview.CardStackLayoutManager

class News : Fragment(), NewsAdapter.ItemListener {

    companion object {
        fun newInstance() = News()
    }

    private lateinit var viewModel: NewsViewModel
    private lateinit var sharePreference: AppSharePreference
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
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponents()
        setupRecyclerView()
        setUpSwipeCard()
    }

    private fun setUpSwipeCard() {
        bind.card.layoutManager = CardStackLayoutManager(requireContext())
        bind.card.adapter = mAdapter
    }

    private fun setupRecyclerView() {
        mAdapter = NewsAdapter(context?.applicationContext!!, this)
        bind.recycler.layoutManager = LinearLayoutManager(requireContext())
        bind.recycler.adapter = mAdapter
    }

    private fun initAllComponents() {
        sharePreference= AppSharePreference(requireContext())
        val apiService: ApiRequests = RetroClient.getClient()
        resporitaryApis = ResporitaryApis(apiService,sharePreference)
        viewModel = getViewModel()
        viewModel.NewsByCountry.observe(viewLifecycleOwner) { bindUi(it) }

        linearLayoutManager = LinearLayoutManager(context)
        bind.recycler.layoutManager = linearLayoutManager


    }

    private fun bindUi(data: TopHeadlineInCountry) {
        if(bind.progressBar.visibility==VISIBLE){
            bind.progressBar.visibility=GONE
        }
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

    override fun onClickedArticle(url: String) {
        val action = NewsDirections.newsToMWebView(url)
        view?.findNavController()?.navigate(action)

    }

    override fun onClickedShare(
        title: String,
        description: String,
        urlToImage: String,
        url: String
    ) {
        mLog("Title :: $title Description :: $description UrlToImage :: $urlToImage URL:: $url")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Hey, Just found a awesome app for you,\n\n" + getString(R.string.PlayStore) + context?.packageName
                )
                sendIntent.type = "text/plain"
                startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.send_to)))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
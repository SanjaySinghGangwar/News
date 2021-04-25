package com.theaverageguy.news.mViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.theaverageguy.news.Repository.ResporitaryApis
import com.theaverageguy.news.Utils.NetworkState
import com.theaverageguy.news.modelClass.mByCountry.TopHeadlineInCountry
import io.reactivex.disposables.CompositeDisposable

class NewsViewModel(private val repositoryApis: ResporitaryApis) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val NewsByCountry: LiveData<TopHeadlineInCountry> by lazy {

        repositoryApis.fetchTopNewsInCountry(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        repositoryApis.getNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
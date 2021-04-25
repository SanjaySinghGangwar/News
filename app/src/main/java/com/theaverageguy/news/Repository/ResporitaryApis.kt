package com.theaverageguy.news.Repository

import androidx.lifecycle.LiveData
import com.theaverageguy.news.Utils.NetworkState
import com.theaverageguy.news.mRetrofit.ApiRequests
import com.theaverageguy.news.modelClass.mByCountry.TopHeadlineInCountry
import io.reactivex.disposables.CompositeDisposable

class ResporitaryApis(private val apiService: ApiRequests) {

    lateinit var byCountryNetworkDataSource: ByCountryNetworkDataSource

    fun fetchTopNewsInCountry(
        compositeDisposable: CompositeDisposable,
    ): LiveData<TopHeadlineInCountry> {

        byCountryNetworkDataSource = ByCountryNetworkDataSource(apiService, compositeDisposable)
        byCountryNetworkDataSource.fetchTopNews()

        return byCountryNetworkDataSource.AllNewsResponse

    }

    fun getNetworkState(): LiveData<NetworkState> {
        return byCountryNetworkDataSource.networkState
    }

}
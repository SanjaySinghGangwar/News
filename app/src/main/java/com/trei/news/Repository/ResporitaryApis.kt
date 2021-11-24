package com.trei.news.Repository

import androidx.lifecycle.LiveData
import com.trei.news.Utils.NetworkState
import com.trei.news.mRetrofit.ApiRequests
import com.trei.news.modelClass.mByCountry.TopHeadlineInCountry
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
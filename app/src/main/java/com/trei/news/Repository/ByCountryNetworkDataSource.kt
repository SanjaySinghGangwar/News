package com.trei.news.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trei.news.Private.Keys.API_KEY
import com.trei.news.Utils.NetworkState
import com.trei.news.mRetrofit.ApiRequests
import com.trei.news.modelClass.mByCountry.TopHeadlineInCountry
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ByCountryNetworkDataSource(
    private val apiService: ApiRequests,
    private val compositeDisposable: CompositeDisposable
) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _topHeadlineInCountry = MutableLiveData<TopHeadlineInCountry>()
    val AllNewsResponse: LiveData<TopHeadlineInCountry>
        get() = _topHeadlineInCountry

    fun fetchTopNews() {
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiService.getTopHeadlines("in", API_KEY)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _topHeadlineInCountry.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("MovieDetailsDataSource", it.message!!)
                        }
                    )
            )

        } catch (e: Exception) {
            Log.e("MovieDetailsDataSource", e.message!!)
        }


    }
}
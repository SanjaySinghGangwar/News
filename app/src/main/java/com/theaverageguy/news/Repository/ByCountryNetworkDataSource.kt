package com.theaverageguy.news.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.theaverageguy.news.Private.Keys.API_KEY
import com.theaverageguy.news.Utils.NetworkState
import com.theaverageguy.news.mRetrofit.ApiRequests
import com.theaverageguy.news.modelClass.mByCountry.TopHeadlineInCountry
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
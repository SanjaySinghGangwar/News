package com.theaverageguy.news.mRetrofit

import com.theaverageguy.news.modelClass.mByCountry.TopHeadlineInCountry
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Single<TopHeadlineInCountry>
}
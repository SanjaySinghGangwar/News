package com.theaverageguy.news.modelClass.mByCountry


import com.google.gson.annotations.SerializedName

data class TopHeadlineInCountry(
    @SerializedName("articles")
    var articles: List<Article>,
    @SerializedName("status")
    var status: String,
    @SerializedName("totalResults")
    var totalResults: Int
)
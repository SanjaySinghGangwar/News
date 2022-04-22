package com.trei.news.modelClass.mBySources


import com.google.gson.annotations.SerializedName

data class BySources(
    @SerializedName("articles")
    var articles: List<Article>,
    @SerializedName("status")
    var status: String,
    @SerializedName("totalResults")
    var totalResults: Int
)
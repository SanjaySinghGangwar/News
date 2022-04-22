package com.trei.news.modelClass.mSources


import com.google.gson.annotations.SerializedName

data class Sources(
    @SerializedName("sources")
    var sources: List<Source>,
    @SerializedName("status")
    var status: String
)
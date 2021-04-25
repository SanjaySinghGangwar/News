package com.theaverageguy.news.modelClass.mByCountry


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    var id: Any,
    @SerializedName("name")
    var name: String
)
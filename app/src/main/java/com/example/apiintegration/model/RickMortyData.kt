package com.example.apiintegration.model


import com.google.gson.annotations.SerializedName

data class RickMortyData(
    @SerializedName("info")
    val info: Info? = null,
    @SerializedName("results")
    val results: List<Result>? = null
)

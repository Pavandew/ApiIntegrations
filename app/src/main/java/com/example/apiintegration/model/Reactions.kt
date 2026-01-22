package com.example.apiintegration.model


import com.google.gson.annotations.SerializedName

data class Reactions(
    @SerializedName("dislikes")
    val dislikes: Int? = null,
    @SerializedName("likes")
    val likes: Int? = null
)
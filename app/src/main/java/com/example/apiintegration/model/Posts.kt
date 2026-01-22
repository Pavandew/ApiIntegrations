package com.example.apiintegration.model


import com.google.gson.annotations.SerializedName

data class Posts(
    @SerializedName("posts")
    val posts: List<Post>? = null
)
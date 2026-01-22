package com.example.apiintegration.model


import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("reactions")
    val reactions: Reactions? = null,
    @SerializedName("tags")
    val tags: List<String?>? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("views")
    val views: Int? = null
)
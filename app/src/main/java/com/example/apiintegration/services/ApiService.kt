package com.example.apiintegration.services

import com.example.apiintegration.model.PostData
import com.example.apiintegration.model.RickMortyData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("posts")
    suspend fun getPost(): Response<List<PostData>>

    @GET("character")
    suspend fun getRickMorty(): Response<RickMortyData>

}
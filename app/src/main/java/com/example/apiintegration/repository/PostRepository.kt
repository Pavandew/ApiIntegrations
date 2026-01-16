package com.example.apiintegration.repository

import com.example.apiintegration.model.PostData
import com.example.apiintegration.services.RetrofitClient
import retrofit2.Response

class PostRepository {

    suspend fun fetchPost(): Response<List<PostData>> {
        return RetrofitClient.apiService.getPost()
    }

}
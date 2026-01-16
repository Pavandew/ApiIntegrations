package com.example.apiintegration.repository

import com.example.apiintegration.model.RickMortyData
import com.example.apiintegration.services.ApiService
import retrofit2.Response

class RickMortyRepository(private val api: ApiService) {

    suspend fun fetchRickMorty(): Response<RickMortyData> {

        return api.getRickMorty()

    }

}
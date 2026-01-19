package com.example.apiintegration.uiState

import com.example.apiintegration.model.Result

sealed class RickMortyDetailUiState() {

    object Loading : RickMortyDetailUiState()

    data class Success(val data: Result) : RickMortyDetailUiState()

    data class Error(val error: String) : RickMortyDetailUiState()
}
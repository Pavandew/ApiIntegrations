package com.example.apiintegration.uiState

import android.os.Message
import com.example.apiintegration.model.RickMortyData

sealed class RickMortyUiState {

    object Loading: RickMortyUiState()

    data class Success(val data: RickMortyData) : RickMortyUiState()

    data class Error(val message: String) : RickMortyUiState()
}
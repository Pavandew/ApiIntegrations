package com.example.apiintegration.uiState

import com.example.apiintegration.model.Posts

sealed class PostsDataUiState {

    object Loading: PostsDataUiState()

    data class Success(val post: Posts) : PostsDataUiState()

    data class Error(val error: String) : PostsDataUiState()
}
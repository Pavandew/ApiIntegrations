package com.example.apiintegration.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.repository.PostRepository
import com.example.apiintegration.uiState.PostsDataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostsDataViewModel: ViewModel() {
    private val TAG = "PostsDataViewModel"

    private val repository = PostRepository()

    private val _postsData = MutableStateFlow<PostsDataUiState>(PostsDataUiState.Loading)
    val postsData: StateFlow<PostsDataUiState> = _postsData

    fun fetchingPosts() {

        Log.d(TAG, "Fetching function called")
        viewModelScope.launch {

            try {
                _postsData.value = PostsDataUiState.Loading
                val response = repository.fetchPostsData()

                if(response.isSuccessful) {
                    val body = response.body()

                    if(body != null) {
                        _postsData.value = PostsDataUiState.Success(body)
                    } else {
                        Log.d(TAG, "Response is Empty")
                        _postsData.value = PostsDataUiState.Error("Response is Empty!")
                    }

                } else {
                    _postsData.value = PostsDataUiState.Error("Error ${response.errorBody()}")
                    Log.d(TAG, "Error message: ${response.message()}")
                }
            }  catch (e: Exception) {
                Log.d(TAG, "Error message: ${e.message}")
                _postsData.value = PostsDataUiState.Error("${e.message}")
            }
        }
    }

}
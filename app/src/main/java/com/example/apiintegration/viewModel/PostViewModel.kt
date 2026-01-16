package com.example.apiintegration.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.model.PostData
import com.example.apiintegration.repository.PostRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel: ViewModel() {
    companion object{
        private const val TAG = "PostViewModel"
    }
    private val postRepository = PostRepository()

    private val _postData = MutableLiveData<List<PostData>>()
    val postData: LiveData<List<PostData>> get() = _postData

    val error = MutableLiveData<String>()

    fun getPostDetail() {

        viewModelScope.launch {
            Log.d(TAG, "Coroutine Launch")
            try {
                val response = postRepository.fetchPost()
//                _postData.value = posts
                if(response.isSuccessful) {

                    Log.d(TAG, "Response Successful: ${response.body()}")
                    _postData.postValue(response.body())
                } else {
                    error.postValue("Error: ${response.errorBody()}")
                    Log.d(TAG, "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d(TAG, "Response is: ${e.message}")
                error.postValue(e.message)
            }
        }

    }
}
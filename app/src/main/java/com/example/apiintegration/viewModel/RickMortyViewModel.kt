package com.example.apiintegration.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.repository.RickMortyRepository
import com.example.apiintegration.services.RetrofitClient
import com.example.apiintegration.uiState.RickMortyUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RickMortyViewModel: ViewModel() {

    companion object{
        private const val TAG = "RickMortyViewModel"
    }

    private val repository = RickMortyRepository(RetrofitClient.apiRickMortyService)

    private val _rickMortyData =  MutableStateFlow<RickMortyUiState>(RickMortyUiState.Loading)
    val rickMortyData = _rickMortyData.asStateFlow()

    init {
        fetchRickMortyData()
    }

    private fun fetchRickMortyData() {

        viewModelScope.launch {

            _rickMortyData.value = RickMortyUiState.Loading
            Log.d(TAG, "Loading....")

            try {
                val response = repository.fetchRickMorty()

                Log.d(TAG, "Response fetching")

                if(response.isSuccessful) {
                    val body = response.body()

                    if(body != null) {
                        Log.d(TAG, "Response Successful: $body")
                        _rickMortyData.value = RickMortyUiState.Success(body)
                    } else {

                        Log.d(TAG, "Empty Response")
                        _rickMortyData.value = RickMortyUiState.Error("Empty Response")
                    }
                }  else {
                    _rickMortyData.value = RickMortyUiState.Error("Error ${response.code()}: ${response.message()}")
                }

            } catch (e: Exception) {
                _rickMortyData.value = RickMortyUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
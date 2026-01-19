package com.example.apiintegration.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.repository.RickMortyRepository
import com.example.apiintegration.services.RetrofitClient
import com.example.apiintegration.uiState.RickMortyDetailUiState
import com.example.apiintegration.uiState.RickMortyUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RickMortyDetailViewModel : ViewModel(){

    companion object{
        private const val TAG = "RickMortyDetailViewModel"
    }

    private val repository = RickMortyRepository(RetrofitClient.apiRickMortyService)

    private val _allData = MutableStateFlow<RickMortyDetailUiState>(RickMortyDetailUiState.Loading)
    val allData : StateFlow<RickMortyDetailUiState> =  _allData

//    init {
//        fetchRickMortyDetailData()
//    }

    fun fetchRickMortyDetailData(id: Int) {

        viewModelScope.launch {

            _allData.value = RickMortyDetailUiState.Loading
            Log.d(TAG, "Fetching detail for id: $id")


            try {
                val response = repository.fetchRickMortyDetail(id)
                Log.d(TAG, "Fetching detail for id: $id")

                if(response.isSuccessful) {
                    val body = response.body()

                    if(body != null) {

                        _allData.value = RickMortyDetailUiState.Success(body)
                        Log.d(TAG, "Response body is: $body")
                    } else {

                        _allData.value = RickMortyDetailUiState.Error("Empty Response")
                        Log.d(TAG, "Response is Empty")

                    }
                 } else {
                     _allData.value = RickMortyDetailUiState.Error("Error ${response.message()}")
                    Log.d(TAG, "Response Error: ${response.message()}")

                }
            } catch (e: Exception) {

                _allData.value = RickMortyDetailUiState.Error(e.message ?: "Unknown Error")
            }
        }



    }
}
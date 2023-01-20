package com.example.saltest.data.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saltest.data.model.User
import com.example.saltest.data.model.UserResponse
import com.example.saltest.repository.HomeRepository
import com.example.saltest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository : HomeRepository): ViewModel() {

    var isLoading = mutableStateOf(false)
    private var _getUserData: MutableLiveData<List<UserResponse>> =
        MutableLiveData<List<UserResponse>>()
    var getUserData: LiveData<List<UserResponse>> = _getUserData


    suspend fun getUserData(): Resource<List<UserResponse>> {
        val result = repository.getUserResponse()
        if (result is Resource.Success) {
            isLoading.value = true
            _getUserData.value = result.data!!
        }

        return result
    }

}
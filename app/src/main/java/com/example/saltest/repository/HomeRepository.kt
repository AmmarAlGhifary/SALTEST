package com.example.saltest.repository

import com.example.saltest.data.model.UserResponse
import com.example.saltest.di.ApiService
import com.example.saltest.utils.Resource
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: ApiService ) {

    suspend fun getUserResponse(): Resource<List<UserResponse>> {
        val response = try {
            apiService.getUser().data
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}


package com.example.saltest.repository

import com.example.saltest.data.model.LoginResponse
import com.example.saltest.di.ApiService
import com.example.saltest.utils.Resource
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun loginUser(email: String, password: String): Resource<LoginResponse> {
        val response = try {
            apiService.login(email, password)
        } catch (e: Exception) {
            return Resource.Error("Error dalam Login: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}
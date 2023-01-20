package com.example.saltest.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.saltest.data.model.LoginResponse
import com.example.saltest.repository.LoginRepository
import com.example.saltest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

   suspend fun loginUser(email: String, password: String): Resource<LoginResponse> {
        return loginRepository.loginUser(email, password)
    }
}
package com.example.saltest.di

import com.example.saltest.data.model.LoginResponse
import com.example.saltest.data.model.User
import retrofit2.http.*

interface ApiService {

    @GET("users")
    suspend fun getUser() : User

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse
}
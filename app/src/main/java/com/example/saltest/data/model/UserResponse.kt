package com.example.saltest.data.model


import androidx.versionedparcelable.VersionedParcelize
import com.example.saltest.data.model.User
import java.io.Serializable

@VersionedParcelize
data class UserResponse(
    val id: Int,
    val first_name: String,
    val email: String,
    val avatar: String
)  : Serializable
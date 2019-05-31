package com.example.myapplication.data

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id") val id: Long,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("login") val name: String
)

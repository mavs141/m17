package com.example.myapplication.data

import com.google.gson.annotations.SerializedName

class UserList(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val users: List<UserInfo>
)
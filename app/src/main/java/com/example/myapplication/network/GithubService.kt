package com.example.myapplication.network

import com.example.myapplication.data.UserList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("/search/users")
    fun getUserInfo(@Query("q") query: String, @Query("page") page: Int): Call<UserList>
}
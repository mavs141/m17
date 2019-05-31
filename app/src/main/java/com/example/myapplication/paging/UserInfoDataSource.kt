package com.example.myapplication.paging

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.myapplication.data.UserInfo
import com.example.myapplication.network.GithubService
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserInfoDataSource(private val queryString: String) : PageKeyedDataSource<Int, UserInfo>(), KoinComponent {
    private val service: GithubService by inject()
    private val INITIAL_PAGE = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, UserInfo>) {
        val response = service.getUserInfo(queryString, INITIAL_PAGE).execute()
        if (response.isSuccessful) {
            response.body()?.run {
                callback.onResult(response.body()!!.users, null, 2)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserInfo>) {
        val response = service.getUserInfo(queryString, params.key).execute()
        if (response.isSuccessful) {
            response.body()?.run {
                callback.onResult(response.body()!!.users, params.key + 1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UserInfo>) {
        val response = service.getUserInfo(queryString, params.key).execute()
        if (response.isSuccessful) {
            response.body()?.run {
                callback.onResult(response.body()!!.users, params.key - 1)
            }
        }
    }

    class UserInfoDataFactory(private val search: String) : DataSource.Factory<Int, UserInfo>() {
        override fun create(): DataSource<Int, UserInfo> {
            return UserInfoDataSource(search)
        }
    }
}
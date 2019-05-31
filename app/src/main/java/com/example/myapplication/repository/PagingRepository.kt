package com.example.myapplication.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.myapplication.ConstValue
import com.example.myapplication.data.UserInfo
import com.example.myapplication.paging.UserInfoDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PagingRepository {
    fun getData(search: String): Observable<PagedList<UserInfo>> {
        val dataSource = UserInfoDataSource.UserInfoDataFactory(search)
        val config = PagedList.Config.Builder()
            .setPageSize(ConstValue.MAX_COUNT_PER_PAGE)
            .setPrefetchDistance(ConstValue.FETCH_THRESHOLD)
            .build()

        return RxPagedListBuilder(dataSource, config)
            .setFetchScheduler(Schedulers.io())
            .setNotifyScheduler(AndroidSchedulers.mainThread())
            .buildObservable()
    }
}
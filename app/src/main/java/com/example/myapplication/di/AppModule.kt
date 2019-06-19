package com.example.myapplication.di

import com.example.myapplication.ConstValue
import com.example.myapplication.network.GithubService
import com.example.myapplication.repository.PagingRepository
import com.example.myapplication.viewmodel.ActivityViewModel
import com.example.myapplication.viewmodel.FragmentViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { FragmentViewModel(get()) }
    viewModel { ActivityViewModel() }
}

val repositoryModule = module {
    factory { PagingRepository() }
}

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideGithubSearchService(get()) }
}

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

fun provideRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(ConstValue.URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

fun provideGithubSearchService(retrofit: Retrofit): GithubService =
    retrofit.create(GithubService::class.java)

val appModule = listOf(viewModelModule, networkModule, repositoryModule)
package com.example.rickandmorty.di

import com.example.core.di.RemoteModule.provideOkHttpClient
import com.example.rickandmorty.data.remote.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { provideOkHttpClient() }
    single { providePostApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun providePostApi(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}


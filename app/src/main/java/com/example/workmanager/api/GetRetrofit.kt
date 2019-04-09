package com.example.workmanager.api

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GetRetrofit {
    companion object {
        fun getRetrofit(): Retrofit {
            val client = OkHttpClient.Builder()
            val builder = Retrofit.Builder()
                .baseUrl("https://official-joke-api.appspot.com/")
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
            return builder.client(client.build()).build()
        }
    }
}
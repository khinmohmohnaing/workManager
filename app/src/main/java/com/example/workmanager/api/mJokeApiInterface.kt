package com.example.workmanager.api


import com.example.workmanager.model.JokeInfo
import retrofit2.Call
import retrofit2.http.GET


interface mJokeApiInterface {

    @GET("random_joke")
    fun getJokeInfo(): Call<JokeInfo>

}
package com.example.workmanager


import retrofit2.Call
import retrofit2.http.GET


interface mJokeApiInterface {

    @GET("random_joke")
    fun getJokeInfo(): Call<JokeInfo>

}
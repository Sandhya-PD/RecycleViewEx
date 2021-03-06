package com.example.recycleviewex



import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceRetrofit {

    private val client=OkHttpClient.Builder()

        .build()

    private val retrofit=Retrofit.Builder()
        .baseUrl("https://thesimplycoder.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service:Class<T>):T{
        return retrofit.create(service)
    }



}
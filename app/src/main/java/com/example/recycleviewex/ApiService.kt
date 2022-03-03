package com.example.recycleviewex


import androidx.lifecycle.LiveData
import retrofit2.Call

import retrofit2.http.GET

interface ApiService {
    @GET("/marvel-heroes")
    fun getHeroList(): Call<HeroList>
 }
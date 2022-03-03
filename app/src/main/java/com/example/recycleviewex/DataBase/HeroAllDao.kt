package com.example.recycleviewex.DataBase

import com.example.recycleviewex.HeroList
import retrofit2.http.GET

interface HeroAllDao {
    @GET("/marvel-heroes")
    suspend fun getAllHero(): ArrayList<HeroList>
}
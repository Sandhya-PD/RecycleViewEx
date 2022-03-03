package com.example.recycleviewex.DataBase

import androidx.room.TypeConverter
import com.example.recycleviewex.HeroList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataClassConverter {
    @TypeConverter
    fun toGenre(json: String): List<HeroList.Data> {
        val type = object : TypeToken<List<HeroList.Data>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(genres: List<HeroList.Data>) = Gson().toJson(genres)
}
package com.example.recycleviewex.DataBase

import androidx.room.TypeConverter
import com.example.recycleviewex.HeroList
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class SettingsValueModelConverter {
    @TypeConverter
    fun toTorrent(json: String): List<HeroList> {
        val type = object : TypeToken<List<HeroList>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(torrent: List<HeroList>): String {
        val type = object: TypeToken<List<HeroList>>() {}.type
        return Gson().toJson(torrent, type)
    }
//    @TypeConverter
//    fun fromSource(source: Source) :String{
//        return source.name
//    }
//    @TypeConverter
//    fun  tvSource(name: String): Source {
//        return Source( name,name)
//    }
//    @TypeConverter
//    fun fromString(value: String?): List<String?>? {
//        val listType: Type = object : TypeToken<List<String?>?>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromArrayList(list: List<String?>?): String? {
//        val gson = Gson()
//        return gson.toJson(list)
//    }

//    @TypeConverter
//    fun fromSource(source: Source): String {
//        return JSONObject().apply {
////            put("id", source.id)
////            put("name", source.name)
//        }.toString()
//    }
//
//    @TypeConverter
//    fun toSource(source: String): Source {
//        val json = JSONObject(source)
//        return Source(json.get("id"), json.getString("name"))
    }


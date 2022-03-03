package com.example.recycleviewex

import android.app.Application
import androidx.room.Room
import com.example.recycleviewex.DataBase.HeroListDatabase

class MyApplication:Application() {
    companion object{
   var database:HeroListDatabase?=null
    }

    override fun onCreate() {
        super.onCreate()
        database= Room.databaseBuilder(applicationContext,HeroListDatabase::class.java,"Hero_database").fallbackToDestructiveMigration().build()

    }
}
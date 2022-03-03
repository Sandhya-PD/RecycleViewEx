package com.example.recycleviewex.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recycleviewex.HeroList

@Database(entities = [HeroList.Data::class],version = 1,exportSchema = false)
@TypeConverters(SettingsValueModelConverter::class,DataClassConverter::class)
abstract class HeroListDatabase:RoomDatabase() {
    abstract fun heroDao():HeroDao

    companion object{

        @Volatile
        private var INSTANCE:HeroListDatabase?=null




        fun getDatabase(context: Context):HeroListDatabase{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            kotlin.synchronized(this){
                val instance:HeroListDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    HeroListDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE=instance
                return instance
            }



        }
    }
}
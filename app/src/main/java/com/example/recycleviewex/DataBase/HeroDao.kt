package com.example.recycleviewex.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recycleviewex.HeroList

@Dao
interface HeroDao {
    @Query("SELECT * FROM HeroLists")
    fun getHeroList(): LiveData<List<HeroList.Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertHero(hero: List<HeroList.Data>)

    @Query("DELETE FROM HeroLists")
     fun deleteAllHero()

}

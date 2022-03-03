package com.example.recycleviewex

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class HeroList(
    @PrimaryKey(autoGenerate = false)

    val code: Int, // 200
    val `data`: List<Data>

) {
    @Entity(tableName = "HeroLists")
    data class Data(
        @PrimaryKey(autoGenerate = true)
        val myid:Int,
        val _id: String, // 5e4c22e41c9d4400004fd91b
        val name: String, // Tony Stark
        val photo: String, // https://thesimplycoder.com/wp-content/uploads/2020/02/iron-man.jpg
        @SerializedName("superhero_name")
        val superhero_name: String // Iron-Man
    )
}
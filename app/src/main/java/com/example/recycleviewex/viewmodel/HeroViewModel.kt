package com.example.recycleviewex.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.recycleviewex.ApiService
import com.example.recycleviewex.DataBase.HeroDao
import com.example.recycleviewex.DataBase.HeroListDatabase
import com.example.recycleviewex.HeroList
import com.example.recycleviewex.Repository.HeroListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call

class HeroViewModel (application: Application) :AndroidViewModel(application){


  private val repository:HeroListRepository
    val heroes: LiveData<List<HeroList.Data>>


    init {
      val heroDao=HeroListDatabase.getDatabase(application).heroDao()
        repository =HeroListRepository(heroDao)
        heroes=repository.allHero
    }



}

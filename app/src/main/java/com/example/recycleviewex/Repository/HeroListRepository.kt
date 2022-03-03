package com.example.recycleviewex.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recycleviewex.ApiService
import com.example.recycleviewex.DataBase.HeroDao
import com.example.recycleviewex.HeroList
import com.example.recycleviewex.MyApplication.Companion.database
import com.example.recycleviewex.ServiceRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HeroListRepository(private val heroDao: HeroDao){

    val allHero:LiveData<List<HeroList.Data>> =heroDao.getHeroList()

    fun getlist():LiveData<List<HeroList.Data>>{
        return heroDao.getHeroList()
    }

//    var recyclerListData: MutableLiveData<HeroList> = MutableLiveData()
////private lateinit var heroDao:HeroDao
////    val readAllData:LiveData<List<HeroList>> =heroDao.getAllHero()
//
//    val TAG=HeroListRepository::class.java
//
//
//
//
//    fun getHeroes(): LiveData<List<HeroList.Data>>? {
//
//
//        return database?.heroDao()?.getHeroList()
//
//    }
//
//    fun ApiCallAndPutInDB() {
//
//        val serviceRetrofit = ServiceRetrofit.buildService(ApiService::class.java)
//        val call = serviceRetrofit.getHeroList()
//
//
//        call.enqueue(object : Callback<HeroList> {
//            override fun onResponse(call: Call<HeroList>, response: Response<HeroList>) {
//                Log.e(TAG.toString(), response.body().toString())
//
//                when (response.code()) {
//                    200 -> {
//                        Thread(kotlinx.coroutines.Runnable {
//                            database?.heroDao()?.deleteAllHero()
//                            database?.heroDao()?.insertHero(response.body()!!.data)
////                        database?.heroDao()?.getHeroList()
//
//
//                        }).start()
//                    }
//                }
//
//                if(response.isSuccessful)
//                {recyclerListData.postValue(response.body())}
//                }
//
//                override fun onFailure(call: Call<HeroList>, t: Throwable) {
//                    Log.e(TAG.toString(), "OOPS something went wrong")
//                }
//
//
//
//        })
//    }

//    @WorkerThread
////
////    fun insertHero(hero: HeroList) {
////        heroDao.insertHero(hero)
////    }


}



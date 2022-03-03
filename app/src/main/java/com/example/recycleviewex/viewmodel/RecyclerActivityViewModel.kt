package com.example.recycleviewex.viewmodel


import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.recycleviewex.*
import retrofit2.Callback
import retrofit2.Response


class RecyclerActivityViewModel:ViewModel() {
    var recyclerListData: MutableLiveData<HeroList> = MutableLiveData()
    private lateinit var progressbar: ProgressBar


    fun getRecyclerListDataObserver(): MutableLiveData<HeroList> {


        return recyclerListData
    }

//    fun getHeroObserver():MutableLiveData<HeroList>{
//        return null
//    }


//    fun readAllData(): HeroListRepository {
//        readAllData().getHeroes()
//    }

//  private var heroListRepository: HeroListRepository=HeroListRepository()

//    fun getAllHeroList(): LiveData<List<HeroList.Data>>?
//    {
//        return heroListRepository.getHeroes()
//    }

//    fun getHerolistFromAPIAndStore()
//    {
//        heroListRepository.ApiCallAndPutInDB()
//    }

    fun getHeroes(): LiveData<List<HeroList.Data>>? {




        return MyApplication.database?.heroDao()?.getHeroList()


    }

//    fun getHeroObserver(): MutableLiveData<HeroList>{
//        return recyclerListData
//    }




//    fun getHeroes(): Flow<List<HeroList>>? {
//        return MyApplication.database?.heroDao()?.getAllHero()
//    }

    fun makeApiCall() {
        val serviceRetrofit = ServiceRetrofit.buildService(ApiService::class.java)
        val call = serviceRetrofit.getHeroList()

        call.enqueue(object : Callback<HeroList> {
            override fun onResponse(
                call: retrofit2.Call<HeroList>,
                response: Response<HeroList>
            ) {
                if (response.isSuccessful) {
                    recyclerListData.setValue(response.body())

                }
                Log.e(TAG.toString(), response.body().toString())

                MyApplication.database?.heroDao()?.deleteAllHero()
                MyApplication.database?.heroDao()?.insertHero(response.body()!!.data)




            }

            override fun onFailure(call: retrofit2.Call<HeroList>, t: Throwable) {
                recyclerListData.postValue(null)
                Log.e(TAG, "OOPS something went wrong")
//                progressbar.visibility= View.GONE
            }

        })
//            override fun onResponse(call: Call<HeroList>, response: Response<HeroList>) {
//                val e = Log.e(TAG, response!!.body().toString())
//
//                if(response.isSuccessful)
//                {recyclerListData.postValue(response.body())}
//
//                when (response.code()) {
//                    200 -> Thread(Runnable {
//                        MyApplication.database?.heroDao()?.deleteAllHero()
//                        MyApplication.database?.heroDao()?.insertHero(response.body()!!)
//
//                    }).start()
//                }
//
//
//            }
//
//            override fun onFailure(call: Call<HeroList>, t: Throwable) {
//                Log.e(TAG, "OOPS something went wrong")
//            }


//        })



    }
}






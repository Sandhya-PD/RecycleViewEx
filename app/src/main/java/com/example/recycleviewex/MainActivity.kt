package com.example.recycleviewex

//import com.example.recycleviewex.Repository.HeroListRepository
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewex.viewmodel.HeroViewModel
import com.example.recycleviewex.viewmodel.RecyclerActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerview:RecyclerView
    private lateinit var newArrayList:ArrayList<HeroList.Data>
    private lateinit var tempArrayList: ArrayList<HeroList.Data>
    private lateinit var progressbar:ProgressBar
    private lateinit var adapter: RecyclerAdapter
    private lateinit var adapter1:RecyclerAdapter
    private lateinit var heroViewModel: HeroViewModel
//    private lateinit var tempArr:List<HeroList.Data>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newRecyclerview=findViewById(R.id.recyclerView)
       progressbar=findViewById(R.id.progressBar)



        newRecyclerview.visibility=View.VISIBLE


//       var llmanager:LinearLayoutManager= LinearLayoutManager(this)
//        llmanager.orientation=(LinearLayoutManager.VERTICAL)

        newRecyclerview.layoutManager=LinearLayoutManager(this)

//    newRecyclerview.layoutManager=llmanager

        newRecyclerview.setHasFixedSize(true)






        val viewModel= ViewModelProvider(this)[RecyclerActivityViewModel::class.java]

//        viewModel.getHeroes()
        if(isNetworkConnected(this))
        {
            viewModel.makeApiCall()
//            viewModel.getHerolistFromAPIAndStore()


        }
        else
        {



//            viewModel.makeApiCall()

            viewModel.getHeroes()
            Toast.makeText(this,
                "No internet found. Showing cached list in the view",
                Toast.LENGTH_LONG).show()

        }
//
//        heroViewModel.heroes.observe(this , Observer{ HeroList.Data->
//
//            newRecyclerview.adapter=RecyclerAdapter(HeroList.Data)
//
//        })


    viewModel.getHeroes()?.observe(this@MainActivity) {
//            Log.e(MainActivity::class.java.canonicalName, HeroList.toString())

        if (it != null) {
            newRecyclerview.apply {

                newArrayList = it as ArrayList<HeroList.Data>
                tempArrayList = newArrayList
                adapter = RecyclerAdapter(newArrayList)

            }
//            adapter.setData(it)

//            adapter = RecyclerAdapter(newArrayList)

            newRecyclerview.adapter = adapter
            adapter.notifyDataSetChanged()
            adapter.setOnClickListener(object : RecyclerAdapter.OnItemClickListener {

                override fun onItemClick(position: Int) {
//                        Toast.makeText(this@MainActivity,"you clicked on item. $position",Toast.LENGTH_LONG).show()
                    val intent = Intent(this@MainActivity, Imageactivity::class.java)
                    intent.putExtra("Image", newArrayList[position].photo)
                    startActivity(intent)
                }

            })
        }

    }









        viewModel.getRecyclerListDataObserver().observe(this, {

            if (it != null) {


                newArrayList = it.data as ArrayList<HeroList.Data>
                tempArrayList = newArrayList
                adapter = RecyclerAdapter(newArrayList)
                newRecyclerview.adapter = adapter

                adapter.setOnClickListener(object : RecyclerAdapter.OnItemClickListener {

                    override fun onItemClick(position: Int) {
//                        Toast.makeText(this@MainActivity,"you clicked on item. $position",Toast.LENGTH_LONG).show()
                        val intent = Intent(this@MainActivity, Imageactivity::class.java)
                        intent.putExtra("Image", newArrayList[position].photo)
                        startActivity(intent)
                    }

                })

                progressbar.visibility = View.GONE

            } else {

//                    viewModel.getHeroes()
//                    viewModel.getHerolistFromAPIAndStore()

//                    val heroes = heroListRepository.getHeroes()
//                    newRecyclerview.adapter=adapter
                Toast.makeText(this, "Error in getting data from api", Toast.LENGTH_LONG).show()
            }


        })
//        progressbar.visibility=View.VISIBLE
//        viewModel.makeApiCall()

    }



    @SuppressLint("MissingPermission")
    private fun isNetworkConnected(context: Context): Boolean {
        val cm:ConnectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager
        val activeNetwork =cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }


    override fun onCreateOptionsMenu(menu: Menu?):Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val item=menu?.findItem(R.id.searchView)
        val searchView= item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val searchText = newText!!.toLowerCase(Locale.getDefault())

                if (searchText.isNotEmpty()) {
                    val iterator = newArrayList.toMutableList()
                    tempArrayList.clear()
                    iterator.forEach {
                        if (it.name.toLowerCase(Locale.getDefault()).contains(searchText)) {

                            tempArrayList.add(it)
                        }

                    }
                    newRecyclerview.adapter!!.notifyDataSetChanged()

                } else {
                    tempArrayList.clear()
                    tempArrayList.addAll(newArrayList)
                    newRecyclerview.adapter!!.notifyDataSetChanged()
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun initListView(){

    }


}


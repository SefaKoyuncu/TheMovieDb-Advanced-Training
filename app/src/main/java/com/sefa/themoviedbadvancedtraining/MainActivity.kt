package com.sefa.themoviedbadvancedtraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sefa.themoviedbadvancedtraining.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RVAdapter
    private var movieList= ArrayList<Results>()
    private lateinit var database: Database
    private lateinit var dao: DaoInterface
    private lateinit var movieListFromAPI: ArrayList<Results>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchUsers()

        binding.cardView.setOnClickListener {

            binding.imageViewBack.visibility=View.VISIBLE
            getMoviesFromDB()


        }

        binding.imageViewBack.setOnClickListener {
            it.visibility=View.GONE
            setRV(movieListFromAPI)
        }
    }

    private fun getMoviesFromDB()
    {
        movieList.clear()
        database=Database.databaseAccess(this@MainActivity)!!
        dao=database.getMoviesDao()

        val job= CoroutineScope(Dispatchers.Main).launch {

            val movieListFromDB= dao.getMovies()

            for(m in movieListFromDB)
            {
                movieList.add(Results(m.poster_path,m.name))
                Log.e("fromDB",m.id.toString()+m.poster_path+m.name)
            }

            setRV(movieList)


        }
    }

    private fun setRV(listMovies:ArrayList<Results>)
    {
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        listMovies.let {
            adapter= RVAdapter(this,it)
        }
        binding.rv.adapter=adapter
    }

    private fun fetchUsers()
    {

        val job= CoroutineScope(Dispatchers.Main).launch {

            try {
                val usersFromApi = RetrofitBuilder.apiService

                movieListFromAPI=usersFromApi.getPopularMovies().body()!!.results
                setRV(movieListFromAPI)

                Log.e("api", usersFromApi.getPopularMovies().body()!!.results[0].toString())
                Log.e("*******","*******")
                Log.e("api", usersFromApi.getPopularMovies().body()!!.results[1].toString())

            } catch (e: Exception) {

                Log.e("exception", e.toString())
            }
        }
    }
}
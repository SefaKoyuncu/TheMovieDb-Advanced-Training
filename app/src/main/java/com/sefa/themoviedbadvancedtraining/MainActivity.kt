package com.sefa.themoviedbadvancedtraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchUsers()


    }

    private fun fetchUsers() {

        val job= CoroutineScope(Dispatchers.IO).launch {

            try {
                val usersFromApi = RetrofitBuilder.apiService
                Log.e("api", usersFromApi.getPopularMovies().body().toString())
                Log.e("*******","*******")

            } catch (e: Exception) {

                Log.e("exception", e.toString())
            }
        }
    }
}
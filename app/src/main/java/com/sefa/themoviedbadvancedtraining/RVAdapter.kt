package com.sefa.themoviedbadvancedtraining

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RVAdapter(private val mContext:Context,
                private val movieList:ArrayList<Results>)
                : RecyclerView.Adapter<RVAdapter.CardviewHolder>()
{
    private lateinit var database: Database
    private lateinit var dao: DaoInterface

    inner class CardviewHolder(view:View) : RecyclerView.ViewHolder(view)
    {
        var imageViewMovie:ImageView
        var imageViewFav:ImageView
        var textViewName:TextView

        init {
            imageViewMovie=view.findViewById(R.id.imageViewMovie)
            imageViewFav=view.findViewById(R.id.imageViewFav)
            textViewName=view.findViewById(R.id.textViewName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardviewHolder {
        val design=LayoutInflater.from(mContext).inflate(R.layout.cardview_design,parent,false)
        return CardviewHolder(design)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: CardviewHolder, position: Int) {

        val movieResult=movieList[position]
        holder.textViewName.text=movieResult.title
        holder.imageViewMovie.load("https://image.tmdb.org/t/p/original"+movieResult.posterPath)

        holder.imageViewFav.setOnClickListener {

            val movie=Movies(0,movieResult.posterPath!!,movieResult.title!!)
            addMovie(movie)

            Log.e("tıklanan film",movie.toString())

            /*val job= CoroutineScope(Dispatchers.Main).launch {
                delay(2000L)
                val gelenListe= dao.getMovies()
                for(m in gelenListe)
                {
                    Log.e("gelen film", m.id.toString()+m.name+m.poster_path)
                }
            }*/
        }
    }

    fun addMovie(movie:Movies)
    {
        database=Database.databaseAccess(mContext)!!
        dao=database.getMoviesDao()

        val job= CoroutineScope(Dispatchers.Main).launch {

            dao.addMovie(movie)

        }
    }
}
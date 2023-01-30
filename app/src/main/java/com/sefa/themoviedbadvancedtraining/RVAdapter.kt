package com.sefa.themoviedbadvancedtraining

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class RVAdapter(private val mContext:Context,
                private val movieList:ArrayList<Results>)
                : RecyclerView.Adapter<RVAdapter.CardviewHolder>()
{
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

        val movie=movieList[position]
        holder.textViewName.text=movie.title
        holder.imageViewMovie.load("https://image.tmdb.org/t/p/w200"+movie.posterPath)

    }
}
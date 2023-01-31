package com.sefa.themoviedbadvancedtraining

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [Movies::class], version = 1)
abstract class Database : RoomDatabase()
{
    abstract fun getMoviesDao() : DaoInterface

    companion object
    {
        var INSTANCE : Database?=null

        fun databaseAccess(context: Context) :Database?
        {
            if (INSTANCE==null)
            {
                synchronized(Database::class){
                    INSTANCE= Room.databaseBuilder(context.applicationContext
                        ,Database::class.java
                        ,"movies.sqlite").createFromAsset("movies.sqlite").build()
                }
            }

            return INSTANCE
        }
    }
}
package com.sefa.themoviedbadvancedtraining

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movies")
data class Movies(@PrimaryKey (autoGenerate = true) @ColumnInfo(name="id") @NotNull var id:Int,
                  @ColumnInfo(name = "poster_path") @NotNull var poster_path:String,
                  @ColumnInfo(name = "name") @NotNull var name:String)
{
}
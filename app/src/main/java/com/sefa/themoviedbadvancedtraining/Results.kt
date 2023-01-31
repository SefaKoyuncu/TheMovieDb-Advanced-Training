package com.sefa.themoviedbadvancedtraining

import com.google.gson.annotations.SerializedName


data class Results (
  @SerializedName("poster_path"       ) var posterPath       : String?        = null,
  @SerializedName("title"             ) var title            : String?        = null
)
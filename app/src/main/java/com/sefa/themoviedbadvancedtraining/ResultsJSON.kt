package com.sefa.themoviedbadvancedtraining

import com.google.gson.annotations.SerializedName


data class ResultsJSON (

  @SerializedName("results"       ) var results      : ArrayList<Results> = arrayListOf()
)
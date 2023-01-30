package com.sefa.themoviedbadvancedtraining

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("3/movie/popular?api_key=c76938ab688628aa0eb7ed3f2111a145&language=en-US&page=1")
    suspend fun getPopularMovies(): Response<ResultsJSON>
}

package com.madd.madd.tmdbkotlin.Fragments.MovieDetail

import com.madd.madd.tmdbkotlin.HTTP.Models.Cast
import com.madd.madd.tmdbkotlin.HTTP.Models.Movie
import com.madd.madd.tmdbkotlin.HTTP.TMDBApi
import com.madd.madd.tmdbkotlin.HTTP.TMDBModule
import retrofit2.Call
import retrofit2.Callback

class MovieDetailModel:MovieDetailContract.Model {


    var tmdbApi:TMDBApi

    constructor(tmdbApi: TMDBApi) {
        this.tmdbApi = tmdbApi
    }


    override fun getMovie(movieId: String): Call<Movie> {
        return tmdbApi.getMovie(movieId,TMDBModule.TMDB_API_KEY,TMDBModule.TMDB_LANGUAGE)
    }

    override fun getMovieCast(movieId: String): Call<Cast> {
        return tmdbApi.getMovieCast(movieId,TMDBModule.TMDB_API_KEY)
    }
}
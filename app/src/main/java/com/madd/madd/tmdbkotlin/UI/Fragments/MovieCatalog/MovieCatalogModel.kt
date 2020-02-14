package com.madd.madd.tmdbkotlin.Fragments.MovieCatalog

import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList
import com.madd.madd.tmdbkotlin.HTTP.TMDBApi
import com.madd.madd.tmdbkotlin.HTTP.TMDBModule
import retrofit2.Call

class MovieCatalogModel: MovieCatalogContract.Model {


    var tmdbApi: TMDBApi? = null
    constructor(tmdbApi: TMDBApi?) {
        this.tmdbApi = tmdbApi
    }

    override fun getMoviePopularList(page: Int): Call<MovieList> {

        return tmdbApi!!.getMoviePopularList(
            TMDBModule.TMDB_API_KEY,
            TMDBModule.TMDB_LANGUAGE,
            page)

    }

    override fun getMovieUpcomingList(page: Int): Call<MovieList> {
        return tmdbApi!!.getMovieUpcomingList(
            TMDBModule.TMDB_API_KEY,
            TMDBModule.TMDB_LANGUAGE,
            page
        )
    }

    override fun getMovieTopRatedList(page: Int): Call<MovieList> {
        return tmdbApi!!.getMovieTopRatedList(
            TMDBModule.TMDB_API_KEY,
            TMDBModule.TMDB_LANGUAGE,
            page
        )
    }
}
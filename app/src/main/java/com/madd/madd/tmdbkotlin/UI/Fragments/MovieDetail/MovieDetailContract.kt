package com.madd.madd.tmdbkotlin.Fragments.MovieDetail

import com.madd.madd.tmdbkotlin.HTTP.Models.Cast
import com.madd.madd.tmdbkotlin.HTTP.Models.Movie
import retrofit2.Call
import retrofit2.Callback

interface MovieDetailContract {

    interface View {

        val movieId: String

        fun showMovie(movie: Movie)
        fun showCast(cast: Cast)

        fun showLoadingProgress()
        fun hideLoadingProgress()
        fun showMovieError()
        fun showCastError()
    }

    interface Presenter {
        fun setView(view: View)

        fun getMovie()
        fun getCast()
    }

    interface Model {

        fun getMovie(movieId: String):Call<Movie>
        fun getMovieCast(movieId: String): Call<Cast>
    }



}
package com.madd.madd.tmdbkotlin.Fragments.MovieCatalog

import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList
import retrofit2.Call


interface MovieCatalogContract {

    interface View{

        var listType:Int
        var page:Int
        var movieList:ArrayList<MovieList.Movie>

        fun showMovieList(movieList: List<MovieList.Movie>, page: Int)
        fun clearMovieList()
        fun showEmptyListError()
        fun showInternetError()
        fun hideError()
        fun showProgressBar()
        fun hideProgressBar()
        fun openMovieDetail(movie: MovieList.Movie)

        interface MovieSelected{
            fun onMovieClick(movie: MovieList.Movie)
        }
    }

    interface Presenter{
        fun setView(view: View)

        fun requestMovieList()
        fun filterMovieList(query: String)
        fun selectMovie(movie: MovieList.Movie)

    }

    interface Model{
        fun getMoviePopularList(page: Int): Call<MovieList>
        fun getMovieUpcomingList(page: Int): Call<MovieList>
        fun getMovieTopRatedList(page: Int): Call<MovieList>
    }


}
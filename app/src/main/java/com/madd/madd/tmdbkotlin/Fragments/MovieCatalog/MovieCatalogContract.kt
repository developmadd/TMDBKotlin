package com.madd.madd.tmdbkotlin.Fragments.MovieCatalog

import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList
import retrofit2.Call


interface MovieCatalogContract {

    interface View{
        fun showMovieList(movieList: List<MovieList.Movie>, page: Int)
        fun clearMovieList()
        fun showEmptyListError()
        fun showInternetError()
        fun hideError()
        fun showProgressBar()
        fun hideProgressBar()
        fun openMovieDetail(movie: MovieList.Movie)

        fun getMovieList(): ArrayList<MovieList.Movie>
        fun getPage(): Int
        fun setPage(page: Int)
        fun getListType(): Int

        interface MovieSelected{
            fun onMovieClick(movie: MovieList.Movie)
        }
    }

    interface Presenter{
        fun setView(view: MovieCatalogContract.View)

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
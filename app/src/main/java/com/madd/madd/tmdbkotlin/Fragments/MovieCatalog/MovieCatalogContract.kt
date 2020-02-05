package com.madd.madd.tmdbkotlin.Fragments.MovieCatalog

import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList
import io.reactivex.Observable

interface MovieCatalogContract {

    interface View{
        fun showMovie(movie: MovieList.Movie)
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

        fun unSubscribeReactive()
    }

    interface Model{
        fun getMoviePopularList(page: Int): Observable<MovieList.Movie>
        fun getMovieUpcomingList(page: Int): Observable<MovieList.Movie>
        fun getMovieTopRatedList(page: Int): Observable<MovieList.Movie>
    }

    interface Repository{
        fun getMoviePopularList(page: Int): Observable<MovieList.Movie>
        fun getMovieUpcomingList(page: Int): Observable<MovieList.Movie>
        fun getMovieTopRatedList(page: Int): Observable<MovieList.Movie>
    }

}
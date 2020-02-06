package com.madd.madd.tmdbkotlin.Fragments.MovieCatalog

import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList
import com.madd.madd.tmdbkotlin.Utilities.Utilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieCatalogPresenter:MovieCatalogContract.Presenter {

    private var model: MovieCatalogContract.Model? = null
    private var view: MovieCatalogContract.View? = null
    private var backupMovieList = ArrayList<MovieList.Movie>()

    constructor(model: MovieCatalogContract.Model?) {
        this.model = model
    }

    override fun setView(view: MovieCatalogContract.View) {
        this.view = view
    }

    override fun requestMovieList() {
        if( view != null ){
            view!!.showProgressBar()
            val page = view!!.page

            val callback = (object : Callback<MovieList>{
                override fun onFailure(call: Call<MovieList>?, t: Throwable?) {
                    view!!.showInternetError()
                    view!!.hideProgressBar()
                }

                override fun onResponse(call: Call<MovieList>?, response: Response<MovieList>?) {
                    val movieList = response!!.body()
                    if(movieList.movieList.isNotEmpty()){
                        view!!.hideError()
                        view!!.showMovieList(movieList.movieList, movieList.page + 1)
                    } else {
                        view!!.showEmptyListError()
                    }
                    view!!.hideProgressBar()

                }

            })

            if( view!!.listType == MovieCatalogFragment.POPULAR_TYPE ){
                model!!.getMoviePopularList(page).enqueue(callback)
            } else if( view!!.listType == MovieCatalogFragment.TOP_RATED_TYPE ){
                model!!.getMovieTopRatedList(page).enqueue(callback)
            } else if( view!!.listType == MovieCatalogFragment.UPCOMING_TYPE ){
                model!!.getMovieUpcomingList(page).enqueue(callback)
            }


        }
    }

    override fun filterMovieList(query: String) {
        if (backupMovieList.isEmpty()) {
            backupMovieList = ArrayList(view!!.movieList)
        }

        val filteredMovieList = ArrayList<MovieList.Movie>()
        view!!.clearMovieList()

        if (query.isNotEmpty()) {
            val compare = Utilities.cleanString(query)
            for (movie in backupMovieList) {

                val original = Utilities.cleanString(movie.title!!)
                if (original.startsWith(compare)) {
                    filteredMovieList.add(movie)
                }
            }
            for (movie in backupMovieList) {

                val original = Utilities.cleanString(movie.title!!)
                if (!original.startsWith(compare) && original.contains(compare)) {
                    filteredMovieList.add(movie)
                }
            }
            if (filteredMovieList.isNotEmpty()) {
                view!!.hideError()
                view!!.showMovieList(filteredMovieList, 1)
            } else {
                view!!.showEmptyListError()
            }

        } else {
            backupMovieList.clear()
            requestMovieList()
        }
    }

    override fun selectMovie(movie: MovieList.Movie) {
        if (view != null) {
            view!!.openMovieDetail(movie)
        }
    }


}
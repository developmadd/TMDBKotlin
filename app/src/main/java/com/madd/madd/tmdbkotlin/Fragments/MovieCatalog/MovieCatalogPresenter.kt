package com.madd.madd.tmdbkotlin.Fragments.MovieCatalog

import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList
import io.reactivex.observers.DisposableObserver

class MovieCatalogPresenter:MovieCatalogContract.Presenter {

    private var model: MovieCatalogContract.Model? = null
    private var view: MovieCatalogContract.View? = null

    constructor(model: MovieCatalogContract.Model?) {
        this.model = model
    }

    override fun setView(view: MovieCatalogContract.View) {
        this.view = view
    }

    override fun requestMovieList() {
        view!!.showProgressBar()
    }

    override fun filterMovieList(query: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun selectMovie(movie: MovieList.Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unSubscribeReactive() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
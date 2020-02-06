package com.madd.madd.tmdbkotlin.Fragments.MovieDetail

import com.madd.madd.tmdbkotlin.HTTP.Models.Cast
import com.madd.madd.tmdbkotlin.HTTP.Models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailPresenter : MovieDetailContract.Presenter{


    private var view:MovieDetailContract.View? = null
    var model:MovieDetailContract.Model? = null

    constructor(model: MovieDetailContract.Model?) {
        this.model = model
    }


    override fun setView(view: MovieDetailContract.View) {
        this.view = view
    }

    override fun getMovie() {
        if (view != null) {
            view!!.showLoadingProgress()

            model!!.getMovie(view!!.movieId).enqueue((object : Callback<Movie>{
                override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                    view!!.showMovieError()
                    view!!.hideLoadingProgress()
                }

                override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                    view!!.showMovie(response!!.body())
                    view!!.hideLoadingProgress()
                }
            }))


        }
    }

    override fun getCast() {
        if(view != null){

            model!!.getMovieCast(view!!.movieId).enqueue(object :Callback<Cast>{
                override fun onFailure(call: Call<Cast>?, t: Throwable?) {
                    view!!.showCastError()
                }

                override fun onResponse(call: Call<Cast>?, response: Response<Cast>?) {
                    if (response!!.body() != null) {
                        view!!.showCast(response.body())
                    } else {
                        view!!.showCastError()
                    }
                }

            })

        }
    }
}
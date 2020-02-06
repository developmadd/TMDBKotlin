package com.madd.madd.tmdbkotlin.Fragments.TVShowDetail

import com.madd.madd.tmdbkotlin.HTTP.Models.Cast
import com.madd.madd.tmdbkotlin.HTTP.Models.TVShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowDetailPresenter: TVShowDetailContract.Presenter {


    private var view:TVShowDetailContract.View? = null
    var model:TVShowDetailContract.Model? = null

    constructor(model: TVShowDetailContract.Model?) {
        this.model = model
    }




    override fun setView(view: TVShowDetailContract.View) {
        this.view = view
    }

    override fun getTVShow() {
        if (view != null) {
            view!!.showLoadingProgress()

            model!!.getTVShow(view!!.tvShowId).enqueue((object : Callback<TVShow> {
                override fun onFailure(call: Call<TVShow>?, t: Throwable?) {
                    view!!.showTVShowError()
                    view!!.hideLoadingProgress()
                }

                override fun onResponse(call: Call<TVShow>?, response: Response<TVShow>?) {
                    view!!.showTVShow(response!!.body())
                    view!!.hideLoadingProgress()
                }
            }))


        }
    }

    override fun getCast() {
        if(view != null){

            model!!.getTVShowCast(view!!.tvShowId).enqueue(object :Callback<Cast>{
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
package com.madd.madd.tmdbkotlin.Fragments.TVShowDetail

import com.madd.madd.tmdbkotlin.HTTP.Models.Cast
import com.madd.madd.tmdbkotlin.HTTP.Models.Movie
import com.madd.madd.tmdbkotlin.HTTP.Models.TVShow
import retrofit2.Call

interface TVShowDetailContract {

    interface View {

        val tvShowId: String

        fun showTVShow(tvShow: TVShow)
        fun showCast(cast: Cast)

        fun showLoadingProgress()
        fun hideLoadingProgress()
        fun showTVShowError()
        fun showCastError()
    }

    interface Presenter {
        fun setView(view: View)

        fun getTVShow()
        fun getCast()
    }

    interface Model {

        fun getTVShow(tvShowId: String): Call<TVShow>
        fun getTVShowCast(tvShowId: String): Call<Cast>
    }

}
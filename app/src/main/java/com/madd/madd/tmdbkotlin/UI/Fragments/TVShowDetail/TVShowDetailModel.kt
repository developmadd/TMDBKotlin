package com.madd.madd.tmdbkotlin.Fragments.TVShowDetail

import com.madd.madd.tmdbkotlin.HTTP.Models.Cast
import com.madd.madd.tmdbkotlin.HTTP.Models.TVShow
import com.madd.madd.tmdbkotlin.HTTP.TMDBApi
import com.madd.madd.tmdbkotlin.HTTP.TMDBModule
import retrofit2.Call

class TVShowDetailModel: TVShowDetailContract.Model {


    var tmdbApi: TMDBApi

    constructor(tmdbApi: TMDBApi) {
        this.tmdbApi = tmdbApi
    }

    override fun getTVShow(tvShowId: String): Call<TVShow> {
        return tmdbApi.getTVShow(tvShowId, TMDBModule.TMDB_API_KEY, TMDBModule.TMDB_LANGUAGE)
    }

    override fun getTVShowCast(tvShowId: String): Call<Cast> {
        return tmdbApi.getTVShowCast(tvShowId,TMDBModule.TMDB_API_KEY)
    }
}
package com.madd.madd.tmdbkotlin.Fragments.TVShowCatalog

import com.madd.madd.tmdbkotlin.HTTP.Models.TVShowList
import com.madd.madd.tmdbkotlin.HTTP.TMDBApi
import com.madd.madd.tmdbkotlin.HTTP.TMDBModule
import retrofit2.Call

class TVShowCatalogModel: TVShowCatalogContract.Model {



    var tmdbApi:TMDBApi? = null

    constructor(tmdbApi: TMDBApi?) {
        this.tmdbApi = tmdbApi
    }



    override fun getTVShowPopularList(page: Int): Call<TVShowList> {
        return tmdbApi!!.getTVShowPopularList(
            TMDBModule.TMDB_API_KEY,
            TMDBModule.TMDB_LANGUAGE,
            page)
    }

    override fun getTVShowTopRatedList(page: Int): Call<TVShowList> {
        return tmdbApi!!.getTVShowTopRatedList(
            TMDBModule.TMDB_API_KEY,
            TMDBModule.TMDB_LANGUAGE,
            page)
    }

    override fun getTVShowOnAirList(page: Int): Call<TVShowList> {
        return tmdbApi!!.getTVShowOnAirList(
            TMDBModule.TMDB_API_KEY,
            TMDBModule.TMDB_LANGUAGE,
            page)
    }

}
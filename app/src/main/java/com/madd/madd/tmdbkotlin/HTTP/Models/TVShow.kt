package com.madd.madd.tmdbkotlin.HTTP.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TVShow {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
        get() = "https://image.tmdb.org/t/p/w500/" + field!!

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
        get() = "https://image.tmdb.org/t/p/w500/" + field!!

    @SerializedName("genres")
    @Expose
    private var genres: List<Genre>? = null

    @SerializedName("first_air_date")
    @Expose
    var year: String? = null
        get() = field!!.substring(0, 4)

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("number_of_episodes")
    @Expose
    var episodesNumber: String? = null

    @SerializedName("number_of_seasons")
    @Expose
    var seasonsNumber: String? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: String? = null

    val genre: String?
        get() = genres!![0].name



    inner class Genre {

        @SerializedName("name")
        @Expose
        var name: String? = null

    }


}

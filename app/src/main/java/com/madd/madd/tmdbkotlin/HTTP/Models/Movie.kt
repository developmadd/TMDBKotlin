package com.madd.madd.tmdbkotlin.HTTP.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Movie {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
        get() = if (field != null && !field!!.isEmpty()) "https://image.tmdb.org/t/p/w500/" + field!! else ""

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
        get() = if (field != null && !field!!.isEmpty()) "https://image.tmdb.org/t/p/w500/" + field!! else ""

    @SerializedName("genres")
    @Expose
    private val genres: List<Genre>? = null

    @SerializedName("release_date")
    @Expose
    var year: String? = null
        get() = field!!.substring(0, 4)

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: String? = null

    val genre: String?
        get() = if (genres != null && !genres.isEmpty()) genres[0].name else ""







    inner class Genre {

        @SerializedName("name")
        @Expose
        var name: String? = null

    }


}

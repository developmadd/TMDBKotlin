package com.madd.madd.tmdbkotlin.HTTP.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieList {

    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("results")
    @Expose
    var movieList: ArrayList<Movie> = ArrayList()



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


        constructor() {}

        constructor(id: String, title: String, posterPath: String) {
            this.id = id
            this.title = title
            this.posterPath = posterPath
        }

    }


}

package com.madd.madd.tmdbkotlin.HTTP.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TVShowList {

    @SerializedName("page")
    @Expose
    var page: Int = 0

    @SerializedName("results")
    @Expose
    val tvShowList: ArrayList<TVShow> = ArrayList()






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

        constructor() {

        }

        constructor(id: String, name: String, posterPath: String) {
            this.id = id
            this.name = name
            this.posterPath = posterPath
        }


    }

}

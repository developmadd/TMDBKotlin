package com.madd.madd.tmdbkotlin.HTTP.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cast {

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("cast")
    @Expose
    var actorList: ArrayList<Actor> = ArrayList()

    inner class Actor {

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("character")
        @Expose
        var character: String? = null
            get() = if (field != null && !field!!.isEmpty()) field else "Sin nombre"

        @SerializedName("profile_path")
        @Expose
        var profilePath: String? = null
            get() = if (field != null) "https://image.tmdb.org/t/p/w500/" + field!! else ""
    }


}

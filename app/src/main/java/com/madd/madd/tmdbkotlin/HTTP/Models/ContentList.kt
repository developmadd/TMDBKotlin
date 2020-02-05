package com.madd.madd.tmdbkotlin.HTTP.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ContentList {

    @SerializedName("page")
    @Expose
    var page: Int = 0

    @SerializedName("results")
    @Expose
    var contentList: ArrayList<Content> = ArrayList()



    constructor(page: Int, contentList: ArrayList<Content>) {
        this.page = page
        this.contentList = contentList
    }

    constructor() {

    }

    class Content {

        @SerializedName("id")
        @Expose
        private var id: String? = null

        @SerializedName("name")
        @Expose
        private val name: String? = null

        @SerializedName("title")
        @Expose
        private val title: String? = null

        @SerializedName("poster_path")
        @Expose
        private var posterPath: String? = null
            get() = "https://image.tmdb.org/t/p/w500/" + field!!




        val isMovie: Boolean
            get() = name == null


        fun getName(): String {
            return name ?: title!!
        }

    }

}

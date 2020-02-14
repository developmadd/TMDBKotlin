package com.madd.madd.tmdbkotlin.Fragments.TVShowCatalog

import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList
import com.madd.madd.tmdbkotlin.HTTP.Models.TVShowList
import com.madd.madd.tmdbkotlin.Utilities.Utilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVSHowCatalogPresenter:TVShowCatalogContract.Presenter {


    private var view:TVShowCatalogContract.View? = null
    private var model:TVShowCatalogContract.Model? = null
    private var backUpTVShowList = ArrayList<TVShowList.TVShow>()

    constructor(model: TVShowCatalogContract.Model?) {
        this.model = model
    }


    override fun setView(view: TVShowCatalogContract.View) {
        this.view = view
    }

    override fun requestTVShowList() {
        if( view != null ){
            val page = view!!.page
            val callback = object : Callback<TVShowList>{
                override fun onFailure(call: Call<TVShowList>?, t: Throwable?) {
                    view!!.showInternetError()
                    view!!.hideProgressBar()
                }

                override fun onResponse(call: Call<TVShowList>?, response: Response<TVShowList>?) {
                    val tvShowList = response!!.body()
                    if(tvShowList.tvShowList.isNotEmpty()){
                        view!!.hideError()
                        view!!.showTVShowList(tvShowList.tvShowList, tvShowList.page + 1)
                    } else {
                        view!!.showEmptyListError()
                    }
                    view!!.hideProgressBar()
                }
            }

            if (view!!.listType == TVShowCatalogFragment.POPULAR_TYPE) {
                model!!.getTVShowPopularList(page).enqueue(callback)
            } else if (view!!.listType == TVShowCatalogFragment.TOP_RATED_TYPE) {
                model!!.getTVShowTopRatedList(page).enqueue(callback)
            } else if (view!!.listType == TVShowCatalogFragment.ON_AIR_TYPE) {
                model!!.getTVShowOnAirList(page).enqueue(callback)
            }



        }
    }

    override fun filterTVShowList(query: String) {
        if (backUpTVShowList.isEmpty()) {
            backUpTVShowList = ArrayList(view!!.tvShowList)
        }

        val filteredTVShowList = ArrayList<TVShowList.TVShow>()
        view!!.clearTVShowList()

        if (query.isNotEmpty()) {
            val compare = Utilities.cleanString(query)
            for (tvShow in backUpTVShowList) {

                val original = Utilities.cleanString(tvShow.name!!)
                if (original.startsWith(compare)) {
                    filteredTVShowList.add(tvShow)
                }
            }
            for (movie in backUpTVShowList) {

                val original = Utilities.cleanString(movie.name!!)
                if (!original.startsWith(compare) && original.contains(compare)) {
                    filteredTVShowList.add(movie)
                }
            }
            if (filteredTVShowList.isNotEmpty()) {
                view!!.hideError()
                view!!.showTVShowList(filteredTVShowList, 1)
            } else {
                view!!.showEmptyListError()
            }

        } else {
            backUpTVShowList.clear()
            requestTVShowList()
        }
    }

    override fun selectTVShow(tvShow: TVShowList.TVShow) {
        if (view != null) {
            view!!.openTVShowDetail(tvShow)
        }
    }
}
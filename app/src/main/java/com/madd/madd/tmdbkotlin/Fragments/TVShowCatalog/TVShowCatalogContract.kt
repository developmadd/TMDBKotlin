package com.madd.madd.tmdbkotlin.Fragments.TVShowCatalog

import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList
import com.madd.madd.tmdbkotlin.HTTP.Models.TVShowList
import retrofit2.Call

interface TVShowCatalogContract {

    interface View{

        var listType:Int
        var page:Int
        var tvShowList:ArrayList<TVShowList.TVShow>

        fun showTVShowList(tvShowList: List<TVShowList.TVShow>, page: Int)
        fun clearTVShowList()
        fun showEmptyListError()
        fun showInternetError()
        fun hideError()
        fun showProgressBar()
        fun hideProgressBar()
        fun openTVShowDetail(tvShow: TVShowList.TVShow)


        interface TVShowSelected{
            fun onTVShowClick(tvShow: TVShowList.TVShow)
        }
    }

    interface Presenter{
        fun setView(view: View)

        fun requestTVShowList()
        fun filterTVShowList(query: String)
        fun selectTVShow(tvShow: TVShowList.TVShow)

    }

    interface Model{
        fun getTVShowPopularList(page: Int): Call<TVShowList>
        fun getTVShowTopRatedList(page: Int): Call<TVShowList>
        fun getTVShowOnAirList(page: Int): Call<TVShowList>
    }

}
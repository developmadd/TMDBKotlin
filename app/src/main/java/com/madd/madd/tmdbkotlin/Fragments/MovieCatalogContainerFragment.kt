package com.madd.madd.tmdbkotlin.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.tabs.TabLayout
import com.madd.madd.tmdbkotlin.Fragments.MovieCatalog.MovieCatalogContract
import com.madd.madd.tmdbkotlin.Fragments.MovieCatalog.MovieCatalogFragment
import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList

import com.madd.madd.tmdbkotlin.R
import com.madd.madd.tmdbkotlin.Utilities.TabAdapter

/**
 * A simple [Fragment] subclass.
 */
class MovieCatalogContainerFragment : Fragment() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var v:View? = null

    var moviePopularCatalog:MovieCatalogFragment? = null
    var movieUpcomingCatalog:MovieCatalogFragment? = null
    var movieTopRateCatalog:MovieCatalogFragment? = null

    var onMovieSelected:MovieCatalogContract.View.MovieSelected? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_movie_catalog_container, container, false)
        //ButterKnife.bind(this,v!!)
        createMovieCatalog()
        setViewPager()

        return  v
    }


    fun createMovieCatalog(){
        moviePopularCatalog = MovieCatalogFragment()
        moviePopularCatalog!!.setListType(MovieCatalogFragment.POPULAR_TYPE)
        moviePopularCatalog!!.onMovieSelected = onMovieSelected

        movieUpcomingCatalog = MovieCatalogFragment()
        movieUpcomingCatalog!!.setListType(MovieCatalogFragment.UPCOMING_TYPE)
        moviePopularCatalog!!.onMovieSelected = onMovieSelected

        movieTopRateCatalog = MovieCatalogFragment()
        movieTopRateCatalog!!.setListType(MovieCatalogFragment.TOP_RATED_TYPE)
        moviePopularCatalog!!.onMovieSelected = onMovieSelected
    }


    fun setViewPager(){

        tabLayout = v!!.findViewById(R.id.TAB_Movie_Search)
        viewPager = v!!.findViewById(R.id.VP_Movie_Search)

        val tabAdapter = TabAdapter(childFragmentManager)
        tabAdapter.addFragment(moviePopularCatalog!!,"Más populares")
        tabAdapter.addFragment(movieUpcomingCatalog!!,"Mejor calificada")
        tabAdapter.addFragment(movieTopRateCatalog!!,"Próximo lanzamiento")

        viewPager!!.adapter = tabAdapter
        tabLayout!!.setupWithViewPager(viewPager)
        tabLayout!!.setTabTextColors(
            ContextCompat.getColor(context!!,R.color.colorGray),
            ContextCompat.getColor(context!!,R.color.colorWhite))

    }

}

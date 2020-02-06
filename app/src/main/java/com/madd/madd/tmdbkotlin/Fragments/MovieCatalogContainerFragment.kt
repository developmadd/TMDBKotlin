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


    var moviePopularCatalog:MovieCatalogFragment? = null
    var movieUpcomingCatalog:MovieCatalogFragment? = null
    var movieTopRateCatalog:MovieCatalogFragment? = null

    var onMovieSelected:MovieCatalogContract.View.MovieSelected? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_movie_catalog_container, container, false)

        createMovieCatalog()
        setViewPager(v)

        return v
    }


    fun createMovieCatalog(){
        moviePopularCatalog = MovieCatalogFragment()
        moviePopularCatalog!!.listType = MovieCatalogFragment.POPULAR_TYPE
        moviePopularCatalog!!.onMovieSelected = onMovieSelected

        movieUpcomingCatalog = MovieCatalogFragment()
        movieUpcomingCatalog!!.listType = MovieCatalogFragment.UPCOMING_TYPE
        moviePopularCatalog!!.onMovieSelected = onMovieSelected

        movieTopRateCatalog = MovieCatalogFragment()
        movieTopRateCatalog!!.listType = MovieCatalogFragment.TOP_RATED_TYPE
        moviePopularCatalog!!.onMovieSelected = onMovieSelected
    }


    fun setViewPager(v:View){

        tabLayout = v.findViewById(R.id.TAB_Movie_Search)
        viewPager = v.findViewById(R.id.VP_Movie_Search)

        val tabAdapter = TabAdapter(childFragmentManager)
        tabAdapter.addFragment(moviePopularCatalog!!,"Más populares")
        tabAdapter.addFragment(movieTopRateCatalog!!,"Mejor calificada")
        tabAdapter.addFragment(movieUpcomingCatalog!!,"Próximo lanzamiento")

        viewPager!!.adapter = tabAdapter
        tabLayout!!.setupWithViewPager(viewPager)
        tabLayout!!.setTabTextColors(
            ContextCompat.getColor(context!!,R.color.colorGray),
            ContextCompat.getColor(context!!,R.color.colorWhite))

    }

}

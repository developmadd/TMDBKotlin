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
import com.madd.madd.tmdbkotlin.Fragments.MovieCatalog.MovieCatalogFragment

import com.madd.madd.tmdbkotlin.R
import com.madd.madd.tmdbkotlin.Utilities.TabAdapter

/**
 * A simple [Fragment] subclass.
 */
class MovieCatalogContainerFragment : Fragment() {

    @BindView(R.id.TAB_Movie_Search)    internal var tabLayout: TabLayout? = null
    @BindView(R.id.VP_Movie_Search)     internal var viewPager: ViewPager? = null

    var moviePopularCatalog:MovieCatalogFragment? = null
    var movieUpcomingCatalog:MovieCatalogFragment? = null
    var movieTopRateCatalog:MovieCatalogFragment? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_movie_catalog_container, container, false)
        ButterKnife.bind(this,v)
        createMovieCatalog()
        setViewPager()

        return  v
    }


    fun createMovieCatalog(){
        moviePopularCatalog = MovieCatalogFragment()
        movieUpcomingCatalog = MovieCatalogFragment()
        movieTopRateCatalog = MovieCatalogFragment()
    }


    fun setViewPager(){

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

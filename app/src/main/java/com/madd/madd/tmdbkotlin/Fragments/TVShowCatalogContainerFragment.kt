package com.madd.madd.tmdbkotlin.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.madd.madd.tmdbkotlin.Fragments.TVShowCatalog.TVShowCatalogContract
import com.madd.madd.tmdbkotlin.Fragments.TVShowCatalog.TVShowCatalogFragment

import com.madd.madd.tmdbkotlin.R
import com.madd.madd.tmdbkotlin.Utilities.TabAdapter

/**
 * A simple [Fragment] subclass.
 */
class TVShowCatalogContainerFragment : Fragment() {


    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    var tvShowPopularCatalog:TVShowCatalogFragment? = null
    var tvShowTopRateCatalog:TVShowCatalogFragment? = null
    var tvShowOnAirCatalog:TVShowCatalogFragment? = null

    var onTVShowSelected:TVShowCatalogContract.View.TVShowSelected? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_tvshow_catalog_container, container, false)

        createTVShowCatalog()
        setViewPager(v)

        return v
    }


    private fun createTVShowCatalog(){

        tvShowPopularCatalog = TVShowCatalogFragment()
        tvShowPopularCatalog!!.onTVShowSelected = onTVShowSelected
        tvShowPopularCatalog!!.listType = TVShowCatalogFragment.POPULAR_TYPE

        tvShowTopRateCatalog = TVShowCatalogFragment()
        tvShowTopRateCatalog!!.onTVShowSelected = onTVShowSelected
        tvShowTopRateCatalog!!.listType = TVShowCatalogFragment.TOP_RATED_TYPE

        tvShowOnAirCatalog = TVShowCatalogFragment()
        tvShowOnAirCatalog!!.onTVShowSelected = onTVShowSelected
        tvShowOnAirCatalog!!.listType = TVShowCatalogFragment.ON_AIR_TYPE
    }


    private fun setViewPager(v:View){

        tabLayout = v.findViewById(R.id.TAB_TVShow_Search)
        viewPager = v.findViewById(R.id.VP_TVShow_Search)

        val tabAdapter = TabAdapter(childFragmentManager)
        tabAdapter.addFragment(tvShowPopularCatalog!!,"Más Populares")
        tabAdapter.addFragment(tvShowTopRateCatalog!!,"Mejor Calificadas")
        tabAdapter.addFragment(tvShowOnAirCatalog!!,"Al Aire")

        viewPager!!.adapter = tabAdapter
        tabLayout!!.setupWithViewPager(viewPager)
        tabLayout!!.setTabTextColors(
            ContextCompat.getColor(context!!,R.color.colorGray),
            ContextCompat.getColor(context!!,R.color.colorWhite))

    }


}

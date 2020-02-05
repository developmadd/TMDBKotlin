package com.madd.madd.tmdbkotlin.Activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.madd.madd.tmdbkotlin.DI.App
import com.madd.madd.tmdbkotlin.Fragments.MovieCatalogContainerFragment
import com.madd.madd.tmdbkotlin.Fragments.MovieDetail.MovieDetailFragment
import com.madd.madd.tmdbkotlin.Fragments.TVShowCatalogContainerFragment
import com.madd.madd.tmdbkotlin.Fragments.TVShowDetail.TVShowDetailFragment
import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList
import com.madd.madd.tmdbkotlin.HTTP.Models.TVShowList
import com.madd.madd.tmdbkotlin.R
import com.madd.madd.tmdbkotlin.Utilities.TabAdapter

class MainActivity : AppCompatActivity() {

    private var detailContainer: FrameLayout? = null
    private var movieCatalogContainerFragment = MovieCatalogContainerFragment()
    private var tvShowCatalogContainerFragment = TVShowCatalogContainerFragment()
    private var contentSearchFragment = MovieCatalogContainerFragment()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //application
        bindUI()
        setEvents()

    }


    fun setEvents(){

    }

    fun showMovieDetail( movie: MovieList.Movie ){

        val movieDetailFragment = MovieDetailFragment()
        movieDetailFragment.movieId = movie.id!!

        supportFragmentManager.beginTransaction().replace(R.id.CTNR_Detail,movieDetailFragment)
            .commit()

        showDetail()
    }

    fun showTVShowDetail( tvShow: TVShowList.TVShow ){

        val tvShowDetailFragment = TVShowDetailFragment()
        tvShowDetailFragment.tvShowId = tvShow.id!!

        supportFragmentManager.beginTransaction().replace(R.id.CTNR_Detail,tvShowDetailFragment)
            .commit()

        showDetail()
    }






    fun showDetail(){
        detailContainer!!.alpha = 0f
        detailContainer!!.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            detailContainer!!.animate()
                .alpha(0f)
                .setDuration(500)
                .start()
        }
    }
    fun hideDetail(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            detailContainer!!.animate()
                .alpha(0f)
                .setDuration(500)
                .withEndAction {
                    detailContainer!!.visibility = View.GONE
                }
                .start()
        }
    }








    fun bindUI(){
        val tabLayout = findViewById<TabLayout>(R.id.TAB_Main)
        val viewPager = findViewById<ViewPager>(R.id.VP_Main)

        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(movieCatalogContainerFragment,"Películas")
        tabAdapter.addFragment(tvShowCatalogContainerFragment,"Series")
        tabAdapter.addFragment(contentSearchFragment,"Todo")

        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setTabTextColors(
            ContextCompat.getColor(this,R.color.colorGray),
            ContextCompat.getColor(this,R.color.colorWhite))

        detailContainer = findViewById(R.id.CTNR_Detail)

    }

}

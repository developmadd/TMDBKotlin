package com.madd.madd.tmdbkotlin.DI

import android.app.Application
import com.madd.madd.tmdbkotlin.Fragments.MovieCatalog.MovieCatalogModule
import com.madd.madd.tmdbkotlin.Fragments.MovieDetail.MovieDetailModule
import com.madd.madd.tmdbkotlin.Fragments.TVShowCatalog.TVShowCatalogModule
import com.madd.madd.tmdbkotlin.Fragments.TVShowDetail.TVShowDetailModule
import com.madd.madd.tmdbkotlin.HTTP.TMDBModule

class App : Application() {

    var component: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .tMDBModule(TMDBModule())
            .movieCatalogModule(MovieCatalogModule())
            .tVShowCatalogModule(TVShowCatalogModule())
            .movieDetailModule(MovieDetailModule())
            .tVShowDetailModule(TVShowDetailModule())
            .build()
    }
}

package com.madd.madd.tmdbkotlin.DI

import android.app.Application
import com.madd.madd.tmdbkotlin.Fragments.MovieCatalog.MovieCatalogModule
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
            .build()
    }
}

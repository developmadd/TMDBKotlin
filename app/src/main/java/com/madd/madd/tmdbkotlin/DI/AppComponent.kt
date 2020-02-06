package com.madd.madd.tmdbkotlin.DI

import com.madd.madd.tmdbkotlin.Activities.MainActivity
import com.madd.madd.tmdbkotlin.Fragments.MovieCatalog.MovieCatalogFragment
import com.madd.madd.tmdbkotlin.Fragments.MovieCatalog.MovieCatalogModule
import com.madd.madd.tmdbkotlin.Fragments.MovieDetail.MovieDetailFragment
import com.madd.madd.tmdbkotlin.Fragments.MovieDetail.MovieDetailModule
import com.madd.madd.tmdbkotlin.Fragments.TVShowCatalog.TVShowCatalogFragment
import com.madd.madd.tmdbkotlin.Fragments.TVShowCatalog.TVShowCatalogModule
import com.madd.madd.tmdbkotlin.Fragments.TVShowDetail.TVShowDetailFragment
import com.madd.madd.tmdbkotlin.Fragments.TVShowDetail.TVShowDetailModule
import com.madd.madd.tmdbkotlin.HTTP.TMDBModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    TMDBModule::class,
    MovieCatalogModule::class,
    TVShowCatalogModule::class,
    MovieDetailModule::class,
    TVShowDetailModule::class
])
interface AppComponent {
    fun inject(target: MainActivity)
    fun inject(target: MovieCatalogFragment)
    fun inject(target: TVShowCatalogFragment)
    fun inject(target: MovieDetailFragment)
    fun inject(target: TVShowDetailFragment)
}

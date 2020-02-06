package com.madd.madd.tmdbkotlin.DI

import com.madd.madd.tmdbkotlin.Activities.MainActivity
import com.madd.madd.tmdbkotlin.Fragments.MovieCatalog.MovieCatalogFragment
import com.madd.madd.tmdbkotlin.Fragments.MovieCatalog.MovieCatalogModule
import com.madd.madd.tmdbkotlin.HTTP.TMDBModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    TMDBModule::class,
    MovieCatalogModule::class
])
interface AppComponent {
    fun inject(target: MainActivity)
    fun inject(target: MovieCatalogFragment)
}

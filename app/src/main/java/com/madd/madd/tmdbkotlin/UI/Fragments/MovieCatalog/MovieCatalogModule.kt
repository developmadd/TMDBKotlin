package com.madd.madd.tmdbkotlin.Fragments.MovieCatalog

import com.madd.madd.tmdbkotlin.HTTP.TMDBApi
import dagger.Module
import dagger.Provides

@Module
class MovieCatalogModule {

    @Provides
    fun provideMovieCatalogPresenter(model:MovieCatalogContract.Model):MovieCatalogContract.Presenter {
        return MovieCatalogPresenter(model)
    }

    @Provides
    fun provideMovieCatalogModel(tmdbApi: TMDBApi):MovieCatalogContract.Model{
        return MovieCatalogModel(tmdbApi)
}

}
package com.madd.madd.tmdbkotlin.Fragments.TVShowCatalog

import com.madd.madd.tmdbkotlin.HTTP.TMDBApi
import dagger.Module
import dagger.Provides

@Module
class TVShowCatalogModule {

    @Provides
    fun provideTVShowCatalogPresenter(model: TVShowCatalogContract.Model):TVShowCatalogContract.Presenter {
        return TVSHowCatalogPresenter(model)
    }

    @Provides
    fun provideTVShowCatalogModel(tmdbApi: TMDBApi):TVShowCatalogContract.Model{
        return TVShowCatalogModel(tmdbApi)
    }

}
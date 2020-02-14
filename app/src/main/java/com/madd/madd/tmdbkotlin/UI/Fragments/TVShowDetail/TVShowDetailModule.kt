package com.madd.madd.tmdbkotlin.Fragments.TVShowDetail


import com.madd.madd.tmdbkotlin.HTTP.TMDBApi
import dagger.Module
import dagger.Provides

@Module
class TVShowDetailModule {

    @Provides
    fun provideMovieDetailPresenter(model: TVShowDetailContract.Model): TVShowDetailContract.Presenter{
        return TVShowDetailPresenter(model)
    }

    @Provides
    fun provideMovieDetailModel(tmdbApi: TMDBApi): TVShowDetailContract.Model{
        return TVShowDetailModel(tmdbApi)
    }

}
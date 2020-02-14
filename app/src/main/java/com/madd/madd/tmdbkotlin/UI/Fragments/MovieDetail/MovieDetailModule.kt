package com.madd.madd.tmdbkotlin.Fragments.MovieDetail

import com.madd.madd.tmdbkotlin.HTTP.TMDBApi
import dagger.Module
import dagger.Provides

@Module
class MovieDetailModule {

    @Provides
    fun provideMovieDetailPresenter(model:MovieDetailContract.Model):MovieDetailContract.Presenter{
        return MovieDetailPresenter(model)
    }

    @Provides
    fun provideMovieDetailModel(tmdbApi: TMDBApi):MovieDetailContract.Model{
        return MovieDetailModel(tmdbApi)
    }

}
package com.madd.madd.tmdbkotlin.HTTP

import com.madd.madd.tmdbkotlin.HTTP.Models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movieId:String,
        @Query("api_key") apiKey:String,
        @Query("language") language:String
    ): Call<Movie>

    @GET("movie/popular")
    fun getMoviePopularList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<MovieList>


    @GET("movie/upcoming")
    fun getMovieUpcomingList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<MovieList>


    @GET("movie/top_rated")
    fun getMovieTopRatedList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<MovieList>


    @GET("search/movie")
    fun getMovieListByQuery(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Call<ContentList>


    @GET("movie/{movie_id}/credits")
    fun getMovieCast(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Call<Cast>










    @GET("tv/{tv_show_id}")
    fun getTVShow(
        @Path("tv_show_id") tvShowId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<TVShow>


    @GET("tv/popular")
    fun getTVShowPopularList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TVShowList>


    @GET("tv/on_the_air")
    fun getTVShowOnAirList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TVShowList>


    @GET("tv/top_rated")
    fun getTVShowTopRatedList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TVShowList>


    @GET("search/tv")
    fun getTVShowListByQuery(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Call<ContentList>


    @GET("movie/{tv_show_id}/credits")
    fun getTVShowCast(
        @Path("tv_show_id") tvShowId: String,
        @Query("api_key") apiKey: String
    ): Call<Cast>

}
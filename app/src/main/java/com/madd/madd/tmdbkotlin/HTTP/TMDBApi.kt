package com.madd.madd.tmdbkotlin.HTTP

import com.madd.madd.tmdbkotlin.HTTP.Models.Cast
import com.madd.madd.tmdbkotlin.HTTP.Models.ContentList
import com.madd.madd.tmdbkotlin.HTTP.Models.Movie
import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList
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


}
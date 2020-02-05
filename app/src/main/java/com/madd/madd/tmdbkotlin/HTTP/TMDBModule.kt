package com.madd.madd.tmdbkotlin.HTTP

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class TMDBModule {
    private val BASE_URL = "https://api.themoviedb.org/3/"
    private val CACHE_SIZE = 10 * 1024 * 1024 // 10MB
    companion object {
        var TMDB_API_KEY = "5312d9a66aaa268a0e2aab662d455498"
        var TMDB_PAGINATE_STEP = 20
        var TMDB_LANGUAGE = "es-MX"

    }

    @Provides
    fun provideHttpClient(context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS

        return OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, CACHE_SIZE.toLong()))
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideTMDBApi(context: Context): TMDBApi {
        return provideRetrofit(provideHttpClient(context)).create(TMDBApi::class.java)
    }
}
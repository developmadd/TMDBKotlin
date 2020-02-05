package com.madd.madd.tmdbkotlin.DI

import com.madd.madd.tmdbkotlin.Activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class
])
interface AppComponent {
    fun inject(target: MainActivity)
}

package com.newsapp.di

import com.newsapp.direction.MainScreenDirection
import com.newsapp.direction.SplashScreenDirection
import com.newsapp.direction.impl.MainScreenDirectionImpl
import com.newsapp.direction.impl.SplashScreenDirectionImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {

    @[Binds Singleton]
    fun bindSplashScreenDirection(impl: SplashScreenDirectionImpl): SplashScreenDirection

    @[Binds Singleton]
    fun bindMainScreenDirection(impl: MainScreenDirectionImpl): MainScreenDirection
}
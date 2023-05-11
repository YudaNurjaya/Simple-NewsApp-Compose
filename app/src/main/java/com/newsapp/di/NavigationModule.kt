package com.newsapp.di

import com.newsapp.navigation.AppNavigation
import com.newsapp.navigation.NavigationDispatcher
import com.newsapp.navigation.NavigationHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {

    @Binds
    fun appNavigator(dispatcher: NavigationDispatcher): AppNavigation

    @Binds
    fun navigationHandler(dispatcher: NavigationDispatcher): NavigationHandler
}
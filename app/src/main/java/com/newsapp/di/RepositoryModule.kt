package com.newsapp.di

import com.newsapp.domain.NewsRepository
import com.newsapp.domain.impl.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindRepository(impl: NewsRepositoryImpl) : NewsRepository
}
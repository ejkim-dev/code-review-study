package com.devhoony.data.di

import com.devhoony.data.mapper.GithubMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    @Singleton
    fun provideGithubMapper(): GithubMapper = GithubMapper()
}
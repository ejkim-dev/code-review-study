package com.devhoony.data.di

import com.devhoony.data.repositoryimpl.GithubRepositoryImpl
import com.devhoony.data.repositoryimpl.VodRepositoryImpl
import com.devhoony.domain.repository.GithubRepository
import com.devhoony.domain.repository.VodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    companion object {
        const val BASE_URL = "https://api.github.com" //github api
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
//        if (BuildConfig.DEBUG) {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
//        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideVodRepository(dataSource: VodRepositoryImpl): VodRepository = dataSource

    @Provides
    @Singleton
    fun provideGithubRepository(dataSource: GithubRepositoryImpl): GithubRepository = dataSource
}
package com.devhoony.data.di

import com.devhoony.data.repositoryimpl.GithubRepositoryImpl
import com.devhoony.data.repositoryimpl.VodRepositoryImpl
import com.devhoony.domain.repository.GithubRepository
import com.devhoony.domain.repository.VodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    companion object {
        const val BASE_URL = "https://api.github.com" //github api
        const val BASE_URL_2 =
            "http://1mhomedance-api-20f8820449d30e5e.elb.ap-northeast-2.amazonaws.com/api/" //github api
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory.Companion.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClientBuilder
            .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))

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
    @Named("second")
    fun provideSecondRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_2)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideVodRepository(dataSource: VodRepositoryImpl): VodRepository = dataSource

    @Provides
    @Singleton
    fun provideGithubRepository(dataSource: GithubRepositoryImpl): GithubRepository = dataSource
}
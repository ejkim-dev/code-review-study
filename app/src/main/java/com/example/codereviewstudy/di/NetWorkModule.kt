package com.example.codereviewstudy.di

import com.example.codereviewstudy.network.NetWorkConstant.BASE_URL
import com.example.codereviewstudy.network.NetWorkConstant.CONNECT_TIMEOUT
import com.example.codereviewstudy.network.NetWorkConstant.READ_TIMEOUT
import com.example.codereviewstudy.network.NetWorkConstant.WRITE_TIMEOUT
import com.example.codereviewstudy.network.api.UserInfoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideInterceptor(
    ): Interceptor {
        return Interceptor { chain ->

            val request = chain.request().newBuilder().apply {
                //addHeader("Content-Type", "application/json; charset=utf-8")
               // addHeader("x-app-os", "AND")
            }.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(interceptor)
            .build()
    }


    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun providesUserInfoApi(retrofit: Retrofit): UserInfoApi =
        retrofit.create(UserInfoApi::class.java)

/*
    @Singleton
    @Provides
    fun providesElseApi(okHttpClient: OkHttpClient): ElseApi =
        getElseRetrofit(okHttpClient)
            .create(ElseApi::class.java)


    private fun getElseRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL_ELSE)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()*/

}
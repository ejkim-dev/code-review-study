package com.example.github_repo.data.datasource

import com.example.github_repo.data.api.ServiceApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpDatasource(val targetUrl: String) {

    val api: ServiceApi
    init {
        api = createApi(targetUrl)
    }

    private fun createApi(targetUrl: String): ServiceApi {
        val lastChar = targetUrl[targetUrl.length - 1]
        val resultUrl = if (lastChar != '/') {
            "$targetUrl/"
        } else {
            targetUrl
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level =
                HttpLoggingInterceptor.Level.BASIC
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(resultUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ServiceApi::class.java)
    }
}
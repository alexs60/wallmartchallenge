package com.alessandrofarandagancio.wallmartchallenge.network

import androidx.viewbinding.BuildConfig
import com.alessandrofarandagancio.wallmartchallenge.network.model.State
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface NetworkManager {

    companion object {

        var baseUrl = "https://gist.githubusercontent.com/"

        private val okHttpClient = if (BuildConfig.DEBUG)
        {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        } else
        {
            OkHttpClient
                .Builder()
                .build()
        }

        private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

        val apiService: ApiService = retrofit.create(ApiService::class.java)

    }

    suspend fun getAllStates(): List<State>

}
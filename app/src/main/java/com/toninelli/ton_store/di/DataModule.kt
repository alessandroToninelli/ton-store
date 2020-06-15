package com.toninelli.ton_store.di

import com.example.networkcalladapterlib.NetworkCallAdapterFactory
import com.toninelli.ton_store.data.RepoImpl
import com.toninelli.ton_store.data.api.RemoteApi
import com.toninelli.ton_store.data.Repository
import com.toninelli.ton_store.data.api.ApiMock
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object DataModule {

    val netModule = module {

        single {
            Retrofit.Builder()
                .client(get())
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(NetworkCallAdapterFactory.create())
                .build()
                .create(RemoteApi::class.java)
        }

        single {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BASIC
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
        }

        single<ApiMock>()

    }

    val repoModule = module {
        singleBy<Repository, RepoImpl>()

    }



}


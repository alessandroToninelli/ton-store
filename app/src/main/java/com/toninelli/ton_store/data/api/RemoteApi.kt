package com.toninelli.ton_store.data.api

import com.example.networkcalladapterlib.ResponseNetwork
import com.toninelli.ton_store.model.Beer
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {


    @GET("beers")
    suspend fun getBeers(@Query("page") page: Int, @Query("per_page") perPage: Int): ResponseNetwork<List<Beer>, Any>

}
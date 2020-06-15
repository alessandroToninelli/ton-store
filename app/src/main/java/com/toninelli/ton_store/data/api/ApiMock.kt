package com.toninelli.ton_store.data.api

import com.example.networkcalladapterlib.ResponseNetwork
import com.example.networkcalladapterlib.ResponseNetworkSuccess
import com.toninelli.ton_store.model.Article

object ApiMock{

    suspend fun lastArrived(size: Int): ResponseNetwork<List<Article>, Any>{
        val articles = mutableListOf<Article>()
        repeat(size){
            articles.add(Article(it, "Descrizione Articolo $it", it*2.0))
        }

        return ResponseNetworkSuccess(articles)
    }


}
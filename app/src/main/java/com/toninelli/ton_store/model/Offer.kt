package com.toninelli.ton_store.model

data class Offer(
    val id: Int,
    val name: String,
    val validity: String,
    val description: String,
    val articles: List<Int>
)
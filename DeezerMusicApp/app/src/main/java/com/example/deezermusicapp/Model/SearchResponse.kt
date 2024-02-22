package com.example.deezermusicapp.Model

data class SearchResponse(
    val data: MutableList<Data>,
    val next: String,
    val total: Int
)
package com.example.filmsandserials.data

data class Film(
    val id: Int,
    val name: String,
    val originalName: String,
    val description: String,
    val poster_path: String,
    val backdrop_path: String,
    val popularity: Int,
    val vote_count: Int,
    var isFavorite: Boolean
)

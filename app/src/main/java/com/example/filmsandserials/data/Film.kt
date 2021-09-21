package com.example.filmsandserials.data

data class Film(
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val popularity: Double,
    val vote_average: Double,
    var isFavorite: Boolean = false
)

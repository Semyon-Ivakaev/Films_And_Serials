package com.example.filmsandserials.data

data class Film(
    val id: Int,
    val name: String,
    val url: String,
    var isFavorite: Boolean
)

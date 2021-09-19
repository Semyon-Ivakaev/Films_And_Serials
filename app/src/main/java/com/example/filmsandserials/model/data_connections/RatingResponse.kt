package com.example.filmsandserials.model.data_connections


data class RatingResponse(
    val result: List<RatingResponseResult>
)

data class RatingResponseResult(
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val popularity: Int,
    val vote_average: Double,
    var isFavorite: Boolean
)

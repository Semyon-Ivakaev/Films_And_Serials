package com.example.filmsandserials.model.data_connections

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmResponse(
    @SerializedName("results")
    val results: List<FilmResponseResult>): Serializable

data class FilmResponseResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("vote_average")
    val vote_average: Double,
    var isFavorite: Boolean
)
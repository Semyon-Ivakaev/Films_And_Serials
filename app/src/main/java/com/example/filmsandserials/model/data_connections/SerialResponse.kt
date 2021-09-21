package com.example.filmsandserials.model.data_connections

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerialResponse(
    @SerializedName("results")
    val results: List<SerialResponseResult>): Serializable

data class SerialResponseResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_name")
    val original_name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("vote_average")
    val vote_average: Double,
    var isFavorite: Boolean
)
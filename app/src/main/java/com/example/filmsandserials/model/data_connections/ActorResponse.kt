package com.example.filmsandserials.model.data_connections

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ActorResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profile_path: String,
    @SerializedName("biography")
    val biography: String): Serializable
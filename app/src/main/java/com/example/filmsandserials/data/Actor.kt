package com.example.filmsandserials.data

import java.io.Serializable

data class Actor(
    val id: Int,
    val name: String,
    val profile_path: String,
    val biography: String
): Serializable
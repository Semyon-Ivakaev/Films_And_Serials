package com.example.filmsandserials.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.filmsandserials.model.database.IdsConverter
import java.io.Serializable

@Entity(tableName = "Content")
data class Film(
    @PrimaryKey
    val id: Int,
    val title: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val popularity: Double,
    val vote_average: Double,
    var genre_ids: List<Int>,
    var isFavorite: Boolean = false
): Serializable

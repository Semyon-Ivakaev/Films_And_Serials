package com.example.filmsandserials.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.filmsandserials.data.Film

@Dao
interface ContentDao {

    @Insert
    suspend fun insertContent(film: Film)

    @Delete
    suspend fun deleteContent(film: Film)

    @Query("SELECT * FROM Content")
    suspend fun getAllContent(): List<Film>
}
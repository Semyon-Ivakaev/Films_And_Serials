package com.example.filmsandserials.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.filmsandserials.data.Film

@Dao
interface ContentDao {

    @Insert
    fun insertContent(film: Film)

    @Delete
    fun deleteContent(film: Film)

    @Query("SELECT * FROM Content")
    fun getAllContent(): List<Film>
}
package com.example.filmsandserials.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmsandserials.data.Film

@Database(entities = [Film::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getContentDao(): ContentDao
}
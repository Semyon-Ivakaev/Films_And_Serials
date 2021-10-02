package com.example.filmsandserials

import android.app.Application
import com.example.filmsandserials.model.database.AppDatabase
import com.example.filmsandserials.model.database.FavoriteRepository

class FilmsApplication: Application() {
    val database by lazy {
        AppDatabase.getDatabase(this)
    }
    val repository by lazy {
        FavoriteRepository(database.getContentDao())
    }
}
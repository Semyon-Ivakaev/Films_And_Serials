package com.example.filmsandserials.model.database

import com.example.filmsandserials.data.Film
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val contentDao: ContentDao) {
    val allFavorite: Flow<List<Film>> = contentDao.getAllFavoriteFromDb()
}
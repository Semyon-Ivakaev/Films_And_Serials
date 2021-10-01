package com.example.filmsandserials.viewmodels

import androidx.lifecycle.*
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.model.database.FavoriteRepository

class DBViewModel(private val repository: FavoriteRepository): ViewModel() {
    val allFavorite: LiveData<List<Film>> = repository.allFavorite.asLiveData()

    fun getContentFromDB(): List<Film>? {
        return allFavorite.value
    }
}

class DBViewModelFactory(private val repository: FavoriteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DBViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DBViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
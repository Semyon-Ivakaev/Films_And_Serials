package com.example.filmsandserials.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.model.services.RatingServiceApiImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private var movieList: MutableLiveData<List<Film>> = MutableLiveData()
    private var serialList: MutableLiveData<List<Film>> = MutableLiveData()

    init {
        viewModelScope.launch {
            if (movieList.value == null) {
                movieList.postValue(RatingServiceApiImpl.searchMovies("Пираты", "ru-Ru"))
            }
            if (serialList.value == null) {
                serialList.postValue(RatingServiceApiImpl.searchSerials("Теория", "ru-Ru"))
            }
        }
    }

    fun getMovieList(): LiveData<List<Film>> {
        return movieList
    }

    fun getSerialList(): LiveData<List<Film>> {
        return serialList
    }
}
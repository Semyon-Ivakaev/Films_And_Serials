package com.example.filmsandserials.viewmodels

import android.util.Log
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
                serialList.postValue(RatingServiceApiImpl.searchSerials("улицы", "ru-Ru"))
            }
        }
    }

    fun getMovieList(): LiveData<List<Film>> {
        return movieList
    }

    fun getSerialList(): LiveData<List<Film>> {
        return serialList
    }

    fun refreshMovie(request: String?){
        movieList = MutableLiveData()
        Log.v("APP", "refreshMovie : $request")
        viewModelScope.launch {
            movieList.postValue(RatingServiceApiImpl.searchMovies(request, "ru-Ru"))
        }
    }

    fun refreshSerial(request: String?) {
        serialList = MutableLiveData()
        viewModelScope.launch {
            serialList.postValue(RatingServiceApiImpl.searchSerials(request, "ru-Ru"))
        }
    }
}
package com.example.filmsandserials.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.model.services.RatingServiceApiImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ContentViewModel: ViewModel() {
    private val contentViewModel: MutableLiveData<List<Film>> = MutableLiveData()

    fun loadContent(type: String, top_type: String, lang: String) {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            contentViewModel.postValue(RatingServiceApiImpl.getTopService(type, top_type, lang))
        }
    }

    fun getContent(): LiveData<List<Film>> {
        return contentViewModel
    }
}
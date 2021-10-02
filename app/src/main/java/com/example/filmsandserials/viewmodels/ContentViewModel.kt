package com.example.filmsandserials.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.model.services.ApiServiceApiImpl
import kotlinx.coroutines.launch

class ContentViewModel: ViewModel() {
    private val contentViewModel: MutableLiveData<List<Film>> = MutableLiveData()

    fun loadContent(type: String, top_type: String, lang: String) {
        viewModelScope.launch {
            contentViewModel.postValue(ApiServiceApiImpl.getTopService(type, top_type, lang))
        }
    }

    fun getContent(): LiveData<List<Film>> {
        return contentViewModel
    }

    fun refreshContent(type: String, top_type: String, lang: String) {
        contentViewModel.value = listOf()
        viewModelScope.launch {
            contentViewModel.postValue(ApiServiceApiImpl.getTopService(type, top_type, lang))
        }
    }
}
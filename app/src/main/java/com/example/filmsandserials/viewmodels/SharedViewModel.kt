package com.example.filmsandserials.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {
    val selected = MutableLiveData<List<String>>()

    fun select(item: List<String>) {
        selected.postValue(item)
    }
}
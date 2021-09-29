package com.example.filmsandserials.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {
    val selected = MutableLiveData<String>()

    fun select(item: String) {
        selected.postValue(item)
        Log.v("AppVerbose", "Select $item")
    }

    fun getSelected(): LiveData<String> {
        return selected
    }
}
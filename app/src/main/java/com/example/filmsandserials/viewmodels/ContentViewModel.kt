package com.example.filmsandserials.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.model.database.AppDatabase
import com.example.filmsandserials.model.services.RatingServiceApiImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ContentViewModel: ViewModel() {
    private val contentViewModel: MutableLiveData<List<Film>> = MutableLiveData()
    private var dbViewModel: LiveData<List<Film>>? = null

    fun loadContent(type: String, top_type: String, lang: String) {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            contentViewModel.postValue(RatingServiceApiImpl.getTopService(type, top_type, lang))
        }
    }

    fun getContent(): LiveData<List<Film>> {
        return contentViewModel
    }

    fun refreshContent(type: String, top_type: String, lang: String) {
        contentViewModel.value = listOf()
        CoroutineScope(Dispatchers.IO + Job()).launch {
            contentViewModel.postValue(RatingServiceApiImpl.getTopService(type, top_type, lang))
        }
    }

    fun loadContentFromDB(db: AppDatabase?) {
//        contentViewModel.value = listOf()
        Log.v("App", "loadContentFromDB")
        dbViewModel = db?.getContentDao()?.getAllContentFromDb()
        Log.v("APP", dbViewModel?.value.toString())
    }

    fun getContentFromDB(): LiveData<List<Film>>? {
        return dbViewModel
    }
}
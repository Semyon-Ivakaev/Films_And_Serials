package com.example.filmsandserials.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsandserials.data.Actor
import com.example.filmsandserials.model.services.ApiServiceApiImpl
import kotlinx.coroutines.launch

class ActorsViewModel: ViewModel() {
    private var actorsViewModel: MutableLiveData<List<Actor>> = MutableLiveData()


    fun getRequestForActors(list: List<Int>) {
        viewModelScope.launch {
            actorsViewModel.postValue(ApiServiceApiImpl.getActorDetailInfo(list))
        }
    }

    fun getActors(): LiveData<List<Actor>> {
        return actorsViewModel
    }
}
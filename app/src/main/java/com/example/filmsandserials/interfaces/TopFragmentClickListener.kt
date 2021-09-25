package com.example.filmsandserials.interfaces

import com.example.filmsandserials.data.Film

interface TopFragmentClickListener {
    fun onBackButtonClicked()

    fun onOneViewClicked(film: Film)
}
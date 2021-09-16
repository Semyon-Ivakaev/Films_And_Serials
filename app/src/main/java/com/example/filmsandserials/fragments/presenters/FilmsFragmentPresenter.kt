package com.example.filmsandserials.fragments.presenters

import android.view.View
import com.example.filmsandserials.fragments.FilmsFragment

class FilmsFragmentPresenter {
    private var filmsFragment: FilmsFragment? = null

    fun navigateContentFragment(tag: String) {
        if (tag == "FILMS") {

        }
    }

    fun attachView(view: FilmsFragment) {
        filmsFragment = view
    }

    fun detachView() {
        filmsFragment = null
    }
}
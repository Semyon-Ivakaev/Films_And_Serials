package com.example.filmsandserials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.fragments.MainMenuFragment
import com.example.filmsandserials.fragments.SearchFragment
import com.example.filmsandserials.fragments.StartFragment
import com.example.filmsandserials.fragments.TopFragment
import com.example.filmsandserials.interfaces.MainMenuFragmentInterface
import com.example.filmsandserials.interfaces.SearchFragmentInterface
import com.example.filmsandserials.interfaces.StartFragmentInterface
import com.example.filmsandserials.interfaces.TopFragmentClickListener


class MainActivity : AppCompatActivity(), StartFragmentInterface, MainMenuFragmentInterface,
    SearchFragmentInterface, TopFragmentClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, StartFragment())
                .commit()
        }
    }

    override fun onTypeContentButtonClicked(typeContent: String) {
        Log.v("AppVerbose", "onFilmsButtonClicked")
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainMenuFragment.newInstance(typeContent))
            .addToBackStack(null)
            .commit()
    }

    override fun onBackButtonClicked() {
        Log.v("AppVerbose", "onBackButtonClicked")
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onOneViewClicked(film: Film) {
        Log.v("AppVerbose", "onOneViewClicked")
    }

    override fun onTopTypeButtonClicked(buttonTag: String) {
        Log.v("AppVerbose", "onPopularButtonClicked")
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, TopFragment.newInstance(buttonTag))
            .addToBackStack(null)
            .commit()
    }

    override fun onSearchButtonClicked() {
        Log.v("AppVerbose", "onSearchButtonClicked")
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, SearchFragment())
            .addToBackStack(null)
            .commit()
    }
}
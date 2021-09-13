package com.example.filmsandserials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.filmsandserials.fragments.FilmsFragment
import com.example.filmsandserials.fragments.SearchFragment
import com.example.filmsandserials.fragments.StartFragment
import com.example.filmsandserials.fragments.TopFragment
import com.example.filmsandserials.interfaces.FilmsFragmentInterface
import com.example.filmsandserials.interfaces.SearchFragmentInterface
import com.example.filmsandserials.interfaces.StartFragmentInterface
import com.example.filmsandserials.interfaces.TopFragmentInterface

class MainActivity : AppCompatActivity(), StartFragmentInterface, FilmsFragmentInterface,
    SearchFragmentInterface, TopFragmentInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, StartFragment())
                .commit()
        }
    }

    override fun onFilmsButtonClicked() {
        Log.v("AppVerbose", "onFilmsButtonClicked")
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FilmsFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onSerialsButtonClicked() {
        Log.v("AppVerbose", "onSerialsButtonClicked")
        Toast.makeText(applicationContext, "onSerialsButtonClicked", Toast.LENGTH_SHORT).show()
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

    override fun onPopularButtonClicked(buttonTag: String) {
        Log.v("AppVerbose", "onPopularButtonClicked")
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, TopFragment.newInstance(buttonTag))
            .addToBackStack(null)
            .commit()
    }

    override fun onSearchButtonClicked() {
        Log.v("AppVerbose", "onPopularButtonClicked")
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, SearchFragment())
            .addToBackStack(null)
            .commit()
    }
}
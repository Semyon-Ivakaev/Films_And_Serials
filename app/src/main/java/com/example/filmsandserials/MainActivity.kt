package com.example.filmsandserials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.fragments.*
import com.example.filmsandserials.interfaces.*
import com.example.filmsandserials.model.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), StartFragmentInterface, MainMenuFragmentInterface,
    SearchFragmentInterface, TopFragmentClickListener, DetailFragmentClickListener {
//    private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, StartFragment())
                .commit()
        }

        /*if (db == null) {
            db = Room.databaseBuilder(this, AppDatabase::class.java, "ContentBD").build()
        }*/
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
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, DetailFragment.newInstance(film))
            .addToBackStack(null)
            .commit()
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

    override fun onFavoriteButtonClicked(type: String) {
        Log.v("AppVerbose", "onFavoriteButtonClicked")
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, TopFragment.newInstance(type))
            .addToBackStack(null)
            .commit()
    }
}
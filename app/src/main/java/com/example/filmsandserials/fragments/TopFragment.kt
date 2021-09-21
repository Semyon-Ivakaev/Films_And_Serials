package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsandserials.R
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.databinding.TopFragmentBinding
import com.example.filmsandserials.interfaces.TopFragmentClickListener
import com.example.filmsandserials.model.services.RatingServiceApiImpl
import com.google.gson.Gson
import kotlinx.coroutines.*

class TopFragment:Fragment() {
    lateinit var binding: TopFragmentBinding
    private var topFragmentClickListener: TopFragmentClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TopFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val typeData = arguments?.getString("TAG")
        Log.v("App", typeData!!)
        initViews(typeData)

        // Временное решение, будет перенесено во ViewModel
        CoroutineScope(Dispatchers.IO + Job()).launch {
            var top: List<Film>? = null
            when (typeData) {
                "Rating_FILM" -> top = RatingServiceApiImpl.getTopService("movie", "top_rated","ru-RU")
                "Popular_FILM" -> top = RatingServiceApiImpl.getTopService("movie", "popular","ru-RU")
                "Rating_SERIAL" -> top = RatingServiceApiImpl.getTopService("tv", "top_rated","ru-RU")
                "Popular_SERIAL" -> top = RatingServiceApiImpl.getTopService("tv", "popular","ru-RU")
            }
            delay(5000)
            Log.v("App", top.toString())
        }

        return view
    }

    private fun initViews(typeData: String?) {
        with(binding) {
            backButton.setOnClickListener {
                Log.v("AppVerbose", "Click on Back")
                topFragmentClickListener?.onBackButtonClicked()
            }
            when (typeData) {
                "Popular_FILM" -> topTitle.text = context?.getString(R.string.top_20_popular_film)
                "Popular_SERIAL" -> topTitle.text = context?.getString(R.string.top_20_popular_serials)
                "Rating_FILM" -> topTitle.text = context?.getString(R.string.top_20_rating_film)
                "Rating_SERIAL" -> topTitle.text = context?.getString(R.string.top_20_rating_serial)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TopFragmentClickListener) {
            topFragmentClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        topFragmentClickListener = null
    }

    companion object {
        fun newInstance(buttonTag: String): TopFragment {
            val args = Bundle()
            args.putString("TAG", buttonTag)
            val fragment = TopFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
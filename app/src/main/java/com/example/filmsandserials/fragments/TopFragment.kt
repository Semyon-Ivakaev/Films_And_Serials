package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.filmsandserials.R
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.databinding.TopFragmentBinding
import com.example.filmsandserials.interfaces.TopFragmentClickListener
import com.example.filmsandserials.model.database.AppDatabase
import com.example.filmsandserials.recyclers.FilmsAndSerialsAdapter
import com.example.filmsandserials.viewmodels.ContentViewModel
import kotlinx.coroutines.*

class TopFragment:Fragment() {
    lateinit var binding: TopFragmentBinding
    private var topFragmentClickListener: TopFragmentClickListener? = null
    private val contentViewModel: ContentViewModel by viewModels()
    private var db: AppDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TopFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        /*CoroutineScope(Dispatchers.IO).launch {
            db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "ContentBD").build()
        }*/


        val typeData = arguments?.getString("TAG")
        Log.v("App", typeData!!)
        initViews(typeData)
        initViewModel(typeData)

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
                "DB" -> {
                    topTitle.text = "Мои любимые"
                }
            }
            contentViewModel.getContent().observe(viewLifecycleOwner, {
                    films ->
                val adapter = FilmsAndSerialsAdapter(films, object : TopFragmentClickListener {
                    override fun onBackButtonClicked() {
                        // Этот метод не используется, не стал создавать пока листенер для нажатия отдельный
                        return
                    }

                    override fun onOneViewClicked(film: Film) {
                        topFragmentClickListener?.onOneViewClicked(film)
                    }
                }, db)
                topRecycler.adapter = adapter
                topRecycler.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            })
        }
    }

    private fun initViewModel(typeData: String?) {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            when (typeData) {
                "Rating_FILM" -> {
                    contentViewModel.loadContent("movie", "top_rated", "ru-RU")
                    pullToRefresh("movie", "top_rated", "ru-RU")
                }
                "Popular_FILM" -> {
                    contentViewModel.loadContent("movie", "popular", "ru-RU")
                    pullToRefresh("movie", "popular", "ru-RU")
                }
                "Rating_SERIAL" -> {
                    contentViewModel.loadContent("tv", "top_rated", "ru-RU")
                    pullToRefresh("tv", "top_rated", "ru-RU")
                }
                "Popular_SERIAL" -> {
                    contentViewModel.loadContent("tv", "popular", "ru-RU")
                    pullToRefresh("tv", "popular", "ru-RU")
                }
            }
        }
    }

    private fun pullToRefresh(type: String, top_type: String, lang: String) {
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                contentViewModel.refreshContent(type, top_type, lang)
                swipeRefresh.isRefreshing = false
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
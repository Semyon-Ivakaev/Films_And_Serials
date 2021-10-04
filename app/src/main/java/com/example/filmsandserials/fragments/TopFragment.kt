package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsandserials.FilmsApplication
import com.example.filmsandserials.R
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.databinding.TopFragmentBinding
import com.example.filmsandserials.interfaces.TopFragmentClickListener
import com.example.filmsandserials.model.database.AppDatabase
import com.example.filmsandserials.recyclers.FilmsAndSerialsAdapter
import com.example.filmsandserials.viewmodels.ContentViewModel
import com.example.filmsandserials.viewmodels.DBViewModel
import com.example.filmsandserials.viewmodels.DBViewModelFactory
import kotlinx.coroutines.*

class TopFragment:Fragment() {
    lateinit var binding: TopFragmentBinding
    private var topFragmentClickListener: TopFragmentClickListener? = null
    private val contentViewModel: ContentViewModel by viewModels()
    private var db: AppDatabase? = null
    private val dbViewModel: DBViewModel by activityViewModels {
        DBViewModelFactory(FilmsApplication().repository)
}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TopFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        db = AppDatabase.getDatabase(requireContext())

        val typeData = arguments?.getString("TAG")
        Log.v("App", typeData!!)
        initViews(typeData)
        initViewModel(typeData)

        dbViewModel.allFavorite.observe(viewLifecycleOwner, Observer {
            films -> setAdapter(films)
        })

        return view
    }

    private fun initViews(typeData: String?) {
        with(binding) {
            setProgressBar(true)
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
                    topTitle.text = context?.getString(R.string.favorite_title)
                }
            }
            contentViewModel.getContent().observe(viewLifecycleOwner, {
                    films -> setAdapter(films)
            })
        }
    }

    private fun initViewModel(typeData: String?) {
        Log.v("App", "TypeData : $typeData")
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
                "DB" -> {
                    pullToRefreshFromDb()
                }
            }
        }
    }

    private fun pullToRefresh(type: String, top_type: String, lang: String) {
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                clearAdapter()
                setProgressBar(true)
                contentViewModel.refreshContent(type, top_type, lang)
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun pullToRefreshFromDb() {
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                clearAdapter()
                setProgressBar(true)
                dbViewModel.getContentFromDB()?.let { setAdapter(it) }
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun setAdapter(films: List<Film>) {
        setProgressBar(true)
        val adapter = FilmsAndSerialsAdapter(films, object : TopFragmentClickListener {
            override fun onBackButtonClicked() {
                // Этот метод не используется, не стал создавать пока листенер для нажатия отдельный
                return
            }

            override fun onOneViewClicked(film: Film) {
                topFragmentClickListener?.onOneViewClicked(film)
            }
        })
        with(binding) {
            topRecycler.adapter = adapter
            topRecycler.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        }
        setProgressBar(false)
    }

    private fun setProgressBar(loader: Boolean) {
        Log.v("AppVerbose", "setProgressBar : $loader")
        with(binding) {
            progressBarTop.isVisible = loader
        }
    }

    private fun clearAdapter() {
        with(binding) {
            topRecycler.adapter = null
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
        with(binding) {
            topRecycler.adapter = null
        }
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
package com.example.filmsandserials.fragments.viewpager.tabs

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.databinding.MovieFragmentTabBinding
import com.example.filmsandserials.databinding.SerialFragmentTabBinding
import com.example.filmsandserials.interfaces.TopFragmentClickListener
import com.example.filmsandserials.recyclers.FilmsAndSerialsAdapter
import com.example.filmsandserials.viewmodels.SearchViewModel
import com.example.filmsandserials.viewmodels.SharedViewModel

class MovieFragmentTabs(var searchViewModel: SearchViewModel?): Fragment() {
    lateinit var binding: MovieFragmentTabBinding
    private val model: SharedViewModel by activityViewModels()

    private var topFragmentClickListener: TopFragmentClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieFragmentTabBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        initViewModel()

        return view
    }

    private fun initViewModel() {
        model.selected.observe(viewLifecycleOwner, {
            searchViewModel?.refreshMovie(model.selected.value)
            lookViewModel()
        })
        lookViewModel()
    }

    private fun lookViewModel() {
        searchViewModel?.getMovieList()?.observe(viewLifecycleOwner, { films ->
            initRecyclerAdapter(films)
        })
    }

    private fun initRecyclerAdapter(request: List<Film>) {
        with(binding) {
            searchRecycler.adapter = null
            progressBar.isVisible = true
            val adapter = FilmsAndSerialsAdapter(request, object : TopFragmentClickListener {
                override fun onBackButtonClicked() {
                    // Этот метод не используется, не стал создавать пока листенер для нажатия отдельный
                    return
                }

                override fun onOneViewClicked(film: Film) {
                    topFragmentClickListener?.onOneViewClicked(film)
                }
            }, null)
            searchRecycler.adapter = adapter
            searchRecycler.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            progressBar.isVisible = false
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
}
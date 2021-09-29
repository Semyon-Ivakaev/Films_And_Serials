package com.example.filmsandserials.fragments.viewpager.tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.databinding.SerialFragmentTabBinding
import com.example.filmsandserials.recyclers.FilmsAndSerialsAdapter
import com.example.filmsandserials.viewmodels.SearchViewModel
import com.example.filmsandserials.viewmodels.SharedViewModel

class SerialFragmentTabs(var searchViewModel: SearchViewModel?): Fragment() {
    lateinit var binding: SerialFragmentTabBinding
    private val model: SharedViewModel by activityViewModels()

    private var progressBar: LottieAnimationView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SerialFragmentTabBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        initViewModel()

        return view
    }

    private fun initViewModel() {
        model.selected.observe(viewLifecycleOwner, {
            searchViewModel?.refreshSerial(model.selected.value)
            lookViewModel()

        })
        lookViewModel()
    }

    private fun lookViewModel() {
        searchViewModel?.getSerialList()?.observe(viewLifecycleOwner, { films ->
            initRecyclerAdapter(films)
        })
    }

    private fun initRecyclerAdapter(request: List<Film>) {
        with(binding) {
            searchRecycler.adapter = null
            val adapter = FilmsAndSerialsAdapter(request, null, null)
            searchRecycler.adapter = adapter
            searchRecycler.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        }
    }
}
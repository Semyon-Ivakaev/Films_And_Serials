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
import com.airbnb.lottie.LottieAnimationView
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.databinding.SerialFragmentTabBinding
import com.example.filmsandserials.interfaces.TopFragmentClickListener
import com.example.filmsandserials.recyclers.FilmsAndSerialsAdapter
import com.example.filmsandserials.viewmodels.SearchViewModel
import com.example.filmsandserials.viewmodels.SharedViewModel

class SerialFragmentTabs(var searchViewModel: SearchViewModel?): Fragment() {
    lateinit var binding: SerialFragmentTabBinding
    private val model: SharedViewModel by activityViewModels()

    private var progressBar: LottieAnimationView? = null
    private var topFragmentClickListener: TopFragmentClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SerialFragmentTabBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        with(binding) {
            progressBar.isVisible = true
        }
        initViewModel()

        return view
    }

    private fun initViewModel() {
        var request: String? = null
        model.selected.observe(viewLifecycleOwner, {
            clearAdapter()
            setProgressBar(true)
            request = model.selected.value
            searchViewModel?.refreshSerial(request)
            lookViewModel()
        })
        pullToRefresh(request)
        lookViewModel()
    }

    private fun lookViewModel() {
        searchViewModel?.getSerialList()?.observe(viewLifecycleOwner, { films ->
            setProgressBar(false)
            initRecyclerAdapter(films)
        })
        pullToRefresh(null)
    }

    private fun pullToRefresh(request: String?) {
        var ask = request
        if (request == null) {
            ask = "улицы"
        }
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                setProgressBar(true)
                clearAdapter()
                searchViewModel?.refreshSerial(ask)
                swipeRefresh.isRefreshing = false
                lookViewModel()
            }
        }
    }

    private fun setProgressBar(loader: Boolean) {
        with(binding) {
            progressBar.isVisible = loader
        }
    }

    private fun clearAdapter() {
        with(binding) {
            searchRecycler.adapter = null
        }
    }

    private fun initRecyclerAdapter(request: List<Film>) {
        with(binding) {
            progressBar.isVisible = true
            searchRecycler.adapter = null
            val adapter = FilmsAndSerialsAdapter(request, object : TopFragmentClickListener {
                override fun onBackButtonClicked() {
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
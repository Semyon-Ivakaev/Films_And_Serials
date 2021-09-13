package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsandserials.databinding.FilmsFragmentBinding
import com.example.filmsandserials.interfaces.FilmsFragmentInterface

class FilmsFragment: Fragment() {
    lateinit var binding: FilmsFragmentBinding
    private var filmsFragmentInterface: FilmsFragmentInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FilmsFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        initViews()

        return view
    }

    private fun initViews() {
        with(binding) {
            backButton.setOnClickListener {
                filmsFragmentInterface?.onBackButtonClicked()
            }
            popularCardView.setOnClickListener {
                filmsFragmentInterface?.onPopularButtonClicked("PopularFilm")
            }
            ratingCardView.setOnClickListener {
                filmsFragmentInterface?.onPopularButtonClicked("RatingFilm")
            }
            searchCardView.setOnClickListener {
                filmsFragmentInterface?.onSearchButtonClicked()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FilmsFragmentInterface) {
            filmsFragmentInterface = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        filmsFragmentInterface = null
    }


}
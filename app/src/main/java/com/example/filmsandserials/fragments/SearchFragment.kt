package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsandserials.databinding.SearchFragmentBinding
import com.example.filmsandserials.interfaces.SearchFragmentInterface

class SearchFragment:Fragment() {
    lateinit var binding: SearchFragmentBinding
    private var searchFragmentInterface: SearchFragmentInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        initViews()

        return view
    }

    private fun initViews() {
        with(binding) {
            backButton.setOnClickListener {
                searchFragmentInterface?.onBackButtonClicked()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchFragmentInterface) {
            searchFragmentInterface = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        searchFragmentInterface = null
    }
}
package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsandserials.R
import com.example.filmsandserials.databinding.TopFragmentBinding
import com.example.filmsandserials.interfaces.TopFragmentInterface

class TopFragment:Fragment() {
    lateinit var binding: TopFragmentBinding
    private var topFragmentInterface: TopFragmentInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TopFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val typeData = arguments?.getString("TAG")

        initViews(typeData)

        return view
    }

    private fun initViews(typeData: String?) {
        with(binding) {
            backButton.setOnClickListener {
                Log.v("AppVerbose", "Click on Back")
                topFragmentInterface?.onBackButtonClicked()
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
        if (context is TopFragmentInterface) {
            topFragmentInterface = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        topFragmentInterface = null
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
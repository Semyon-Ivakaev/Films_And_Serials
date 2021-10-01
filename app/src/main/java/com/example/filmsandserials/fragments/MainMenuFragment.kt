package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsandserials.R
import com.example.filmsandserials.databinding.MainMenuFragmentBinding
import com.example.filmsandserials.interfaces.MainMenuFragmentInterface

class MainMenuFragment: Fragment() {
    lateinit var binding: MainMenuFragmentBinding
    private var mainMenuFragmentInterface: MainMenuFragmentInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainMenuFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val fragmentTag = arguments?.getString("TAG")
        initViews(fragmentTag)

        return view
    }

    private fun initViews(fragmentTag: String?) {
        val ratingTag = "Rating_$fragmentTag"
        val popularTag = "Popular_$fragmentTag"

        with(binding) {
            backButton.setOnClickListener {
                mainMenuFragmentInterface?.onBackButtonClicked()
            }
            if (fragmentTag == "FILM") {
                titleRating.text = (context?.getString(R.string.top_20_rating_film))
                titlePopular.text = (context?.getString(R.string.top_20_popular_film))
                titleFavorite.text = (context?.getString(R.string.favorite_film))
            } else {
                titleRating.text = (context?.getString(R.string.top_20_rating_serial))
                titlePopular.text = (context?.getString(R.string.top_20_popular_serials))
                titleFavorite.text = (context?.getString(R.string.favorite_serials))
            }
            popularCardView.setOnClickListener {
                mainMenuFragmentInterface?.onTopTypeButtonClicked(popularTag)
            }
            ratingCardView.setOnClickListener {
                mainMenuFragmentInterface?.onTopTypeButtonClicked(ratingTag)
            }
            searchCardView.setOnClickListener {
                mainMenuFragmentInterface?.onSearchButtonClicked()
            }
            favoriteCardView.setOnClickListener {
                mainMenuFragmentInterface?.onFavoriteButtonClicked("DB")
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainMenuFragmentInterface) {
            mainMenuFragmentInterface = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mainMenuFragmentInterface = null
    }

    companion object {
        fun newInstance(fragmentTag: String) : MainMenuFragment {
            val args = Bundle()
            args.putString("TAG", fragmentTag)
            val fragment = MainMenuFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
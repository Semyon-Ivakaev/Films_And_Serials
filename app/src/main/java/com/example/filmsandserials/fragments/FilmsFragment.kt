package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsandserials.databinding.FilmsFragmentBinding
import com.example.filmsandserials.fragments.presenters.FilmsFragmentPresenter
import com.example.filmsandserials.interfaces.FilmsFragmentInterface

class FilmsFragment: Fragment() {
    private var filmsFragmentPresenter = FilmsFragmentPresenter()
    lateinit var binding: FilmsFragmentBinding
    private var filmsFragmentInterface: FilmsFragmentInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FilmsFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        filmsFragmentPresenter.attachView(this)
        val fragmentTag = arguments?.getString("TAG")
        initViews(fragmentTag)

        return view
    }

    private fun initFilmsView() {

    }

    private fun initViews(fragmentTag: String?) {
        val popularTag = "Popular$fragmentTag"
        val ratingTag = "Rating$fragmentTag"
        with(binding) {
            backButton.setOnClickListener {
                filmsFragmentInterface?.onBackButtonClicked()
            }
            popularCardView.setOnClickListener {
                filmsFragmentInterface?.onPopularButtonClicked(popularTag)
            }
            ratingCardView.setOnClickListener {
                filmsFragmentInterface?.onPopularButtonClicked(ratingTag)
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
        filmsFragmentPresenter.detachView()
    }

    companion object {
        fun newInstance(fragmentTag: String) : FilmsFragment {
            val args = Bundle()
            args.putString("TAG", fragmentTag)
            val fragment = FilmsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
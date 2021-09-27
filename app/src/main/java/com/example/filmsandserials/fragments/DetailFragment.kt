package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.filmsandserials.R
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.databinding.DetailFragmentBinding
import com.example.filmsandserials.interfaces.DetailFragmentClickListener
import com.example.filmsandserials.model.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFragment: Fragment() {
    lateinit var binding: DetailFragmentBinding
    lateinit var view: ConstraintLayout
    private var detailFragmentClickListener: DetailFragmentClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(layoutInflater, container, false)
        view = binding.root

        val content = arguments?.getSerializable("element") as Film
        initViews(content)

        return view
    }

    private fun initViews(content: Film) {
        with(binding) {
            backButton.setOnClickListener {
                detailFragmentClickListener?.onBackButtonClicked()
            }
            topTitle.text = content.title
            originalName.text = content.original_title
            overview.text = content.overview
            downloadPoster(content.backdrop_path)

            viewLifecycleOwner.lifecycleScope.launch {
                drawLiked(checkFavorite(content))
            }
        }
    }

    private suspend fun checkFavorite(element: Film): Boolean{
        return withContext(Dispatchers.IO) {
            AppDatabase.getDatabase(requireContext()).getContentDao().elementIsFavorite(element.original_title)
        }
    }

    private fun drawLiked(like: Boolean) {
        with(binding) {
            if (like) {
                likeContent.setImageResource(R.drawable.ic_like)
            } else {
                likeContent.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DetailFragmentClickListener) {
            detailFragmentClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        detailFragmentClickListener = null
    }

    companion object {
        fun newInstance(element: Film): DetailFragment {
            val args = Bundle()
            args.putSerializable("element", element)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private fun downloadPoster(url: String) {
        with(binding) {
            Glide.with(view)
                .load("https://image.tmdb.org/t/p/w500$url")
                .into(backdropPath)
        }
    }
}
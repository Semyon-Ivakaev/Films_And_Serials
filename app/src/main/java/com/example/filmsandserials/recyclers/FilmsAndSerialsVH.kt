package com.example.filmsandserials.recyclers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmsandserials.R
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.databinding.FilmsAndSerialsVhBinding
import com.example.filmsandserials.interfaces.TopFragmentClickListener

class FilmsAndSerialsVH(
    private var view: View,
    private val clickListener: TopFragmentClickListener?): RecyclerView.ViewHolder(view) {
    private val binding = FilmsAndSerialsVhBinding.bind(view)

    fun bind(element: Film) {
        with(binding) {
            downloadMainPhoto(element.poster_path)
            titleVh.text = element.title
            if (element.isFavorite) {
                likeVh.setImageResource(R.drawable.ic_like)
            } else {
                likeVh.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            mainCardView.setOnClickListener {
                clickListener?.onOneViewClicked(element)
            }
        }
    }

    private fun downloadMainPhoto(url: String) {
        with(binding) {
            Glide.with(view)
                .load("https://image.tmdb.org/t/p/w500$url")
                .into(mainPhotoVh)
        }
    }
}
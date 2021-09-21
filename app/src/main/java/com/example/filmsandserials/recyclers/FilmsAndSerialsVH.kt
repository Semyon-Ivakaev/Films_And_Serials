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
                likeVh.setBackgroundColor(R.color.like_active)
            } else {
                likeVh.setBackgroundColor(R.color.grey)
            }
        }
    }

    private fun downloadMainPhoto(url: String) {
        with(binding) {
            Glide.with(view)
                .load(url)
                .into(mainPhotoVh)
        }
    }
}
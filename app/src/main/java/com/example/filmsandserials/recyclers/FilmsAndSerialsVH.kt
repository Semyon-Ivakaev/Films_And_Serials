package com.example.filmsandserials.recyclers

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmsandserials.R
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.databinding.FilmsAndSerialsVhBinding
import com.example.filmsandserials.interfaces.TopFragmentClickListener
import com.example.filmsandserials.model.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FilmsAndSerialsVH(
    private var view: View,
    private val clickListener: TopFragmentClickListener?,
    private val db: AppDatabase?): RecyclerView.ViewHolder(view) {
    private val binding = FilmsAndSerialsVhBinding.bind(view)

    fun bind(element: Film) {
        with(binding) {
            downloadMainPhoto(element.poster_path)
            titleVh.text = element.title
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

    private fun someActionWithFavorite(element: Film) {
        CoroutineScope(Dispatchers.IO).launch {
            if (!element.isFavorite) {
                element.isFavorite = true
                db?.getContentDao()?.insertContent(element)
                delay(5000)
                Log.v("App", element.toString())
                Log.v("App", db?.getContentDao()?.getAllContent().toString())
            } else {
                element.isFavorite = false
                db?.getContentDao()?.deleteContent(element)
                delay(5000)
                Log.v("App", element.toString())
                Log.v("App", db?.getContentDao()?.getAllContent().toString())
            }
            Log.v("App", db?.getContentDao()?.getAllContent().toString())
        }
    }

    /*private fun drawLikeImage(like: Boolean) {
        Log.v("APP", "DRAW")
        with(binding) {
            if (like) {
                likeVh.setImageResource(R.drawable.ic_like)
            } else {
                likeVh.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
        }
    }*/
}
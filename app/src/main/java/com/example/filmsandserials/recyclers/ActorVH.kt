package com.example.filmsandserials.recyclers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.filmsandserials.R
import com.example.filmsandserials.data.Actor
import com.example.filmsandserials.databinding.ActorVhBinding
import com.example.filmsandserials.model.services.ApiServiceApiImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActorVH(private var view: View): RecyclerView.ViewHolder(view) {
    private val binding = ActorVhBinding.bind(view)

    fun bind(element: Actor) {
        with(binding) {
            downloadMainPhoto(element.profile_path)
            actorName.text = element.name
        }
    }

    private fun downloadMainPhoto(url: String?) {
        with(binding) {
            Glide.with(view)
                .load("https://image.tmdb.org/t/p/w500$url")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.no_photo)
                .into(actorPhoto)
        }
    }
}
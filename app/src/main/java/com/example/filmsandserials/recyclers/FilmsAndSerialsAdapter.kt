package com.example.filmsandserials.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsandserials.R
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.interfaces.TopFragmentClickListener

class FilmsAndSerialsAdapter(
    private val list: List<Film>,
    private val clickListener: TopFragmentClickListener?):
    RecyclerView.Adapter<FilmsAndSerialsVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsAndSerialsVH {
        return FilmsAndSerialsVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.films_and_serials_vh, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: FilmsAndSerialsVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}
package com.example.filmsandserials.recyclers

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsandserials.R
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.interfaces.TopFragmentClickListener
import com.example.filmsandserials.model.database.AppDatabase

class FilmsAndSerialsAdapter(
    private val list: List<Film>,
    private val clickListener: TopFragmentClickListener?, private val db: AppDatabase?):
    RecyclerView.Adapter<FilmsAndSerialsVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsAndSerialsVH {
        return FilmsAndSerialsVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.films_and_serials_vh, parent, false), clickListener, db)
    }

    override fun onBindViewHolder(holder: FilmsAndSerialsVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}
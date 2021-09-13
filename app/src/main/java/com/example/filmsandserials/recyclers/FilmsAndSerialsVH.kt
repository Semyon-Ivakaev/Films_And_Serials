package com.example.filmsandserials.recyclers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsandserials.data.Film
import com.example.filmsandserials.databinding.FilmsAndSerialsVhBinding
import com.example.filmsandserials.interfaces.TopFragmentClickListener

class FilmsAndSerialsVH(
    private var view: View,
    private val clickListener: TopFragmentClickListener?): RecyclerView.ViewHolder(view) {
    private val binding = FilmsAndSerialsVhBinding.bind(view)


    fun bind(element: Film) {

    }
}
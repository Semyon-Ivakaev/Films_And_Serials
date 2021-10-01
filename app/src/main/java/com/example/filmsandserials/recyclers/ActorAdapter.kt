package com.example.filmsandserials.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsandserials.R

class ActorAdapter(
    private val list: List<Int>
):RecyclerView.Adapter<ActorVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorVH {
        return ActorVH(LayoutInflater.from(parent.context).inflate(R.layout.actor_vh, parent, false))
    }

    override fun onBindViewHolder(holder: ActorVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
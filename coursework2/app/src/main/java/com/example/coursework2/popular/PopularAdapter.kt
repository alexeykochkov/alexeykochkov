package com.example.coursework2.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework2.databinding.FragmentCustomViewBinding

class PopularAdapter : RecyclerView.Adapter<PopularViewHolder>() {

    private var popularFilms = mutableListOf<PopularFilms>()
    var onClick: (Int) -> Unit = {

    }


    fun addPoular(popularFilms: List<PopularFilms>) {
        this.popularFilms.addAll(popularFilms)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            FragmentCustomViewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )
    }

    override fun getItemCount(): Int {
        return popularFilms.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(popularFilms[position])
    }


}
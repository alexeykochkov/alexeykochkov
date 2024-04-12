package com.example.coursework2.popular

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coursework2.databinding.FragmentCustomViewBinding

class PopularViewHolder (val binding: FragmentCustomViewBinding, val onClick: (Int) -> Unit): RecyclerView.ViewHolder(binding.root) {

    fun bind(popularFilms: PopularFilms) {
        binding.text1.text = popularFilms.nameRu
        binding.text2.text = popularFilms.genres.joinToString{""}
        binding.text3.text = popularFilms.ratingKinopoisk.toString()
        Glide.with(binding.imageFilm).load(popularFilms.posterUrlPreview).into(binding.imageFilm)
        binding.root.setOnClickListener {
            onClick(popularFilms.kinopoiskId)
        }
    }
}
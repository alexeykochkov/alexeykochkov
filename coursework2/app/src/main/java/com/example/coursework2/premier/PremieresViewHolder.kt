package com.example.coursework2.premier

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coursework2.R
import com.example.coursework2.databinding.FragmentCustomViewBinding

class PremieresViewHolder(val binding: FragmentCustomViewBinding, val onClick: (PremierFilm) -> Unit): RecyclerView.ViewHolder(binding.root) {

    fun bind(primiereFilms: PremierFilm): Unit {
        binding.text1.text = primiereFilms.nameRu
        binding.text2.text = primiereFilms.genres.joinToString()
     Glide.with(binding.imageFilm).load(primiereFilms.posterUrlPreview).into(binding.imageFilm)
        binding.root.setOnClickListener {
            onClick(primiereFilms)
        }
        if (primiereFilms.viewed) {
        binding.imageFilm.foreground = itemView.resources.getDrawable(R.drawable.background_gray_to_films)
        }else {
            binding.imageFilm.foreground = null
        }
    }
}
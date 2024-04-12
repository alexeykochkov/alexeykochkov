package com.example.coursework2.staff

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coursework2.databinding.FragmentCustomViewSecondBinding
import com.example.coursework2.popular.PopularFilms

class StaffViewHolder(val binding: FragmentCustomViewSecondBinding, val onClick: (StaffInfo) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(staffInfo: StaffInfo) {
        binding.text1.text = staffInfo.nameRu
        binding.text2.text = staffInfo.description

        Glide.with(binding.imageFilm).load(staffInfo.posterUrl).into(binding.imageFilm)

        binding.root.setOnClickListener {
           onClick(staffInfo)
        }
    }

}
package com.example.coursework2.premier

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework2.databinding.FragmentCustomViewBinding

class PremieresAdapter (val onClick: (PremierFilm) -> Unit) : RecyclerView.Adapter<PremieresViewHolder>() {

    private var primiereFilms = mutableListOf<PremierFilm>()


    fun addPrimieres(premiersFilms: List<PremierFilm>) {
        this.primiereFilms.addAll(premiersFilms)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremieresViewHolder {
        return PremieresViewHolder(
            FragmentCustomViewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )
    }

    override fun getItemCount(): Int {
        return primiereFilms.size
    }

    override fun onBindViewHolder(holder: PremieresViewHolder, position: Int) {
        holder.bind(primiereFilms[position])
    }

    private fun getInfoById(kinopoiskId: Int): PremierFilm {
        for (film in primiereFilms) {
            if (film.kinopoiskId == kinopoiskId) {
                return film
            }
        }
        throw IllegalArgumentException("message")
    }
}
package com.example.coursework2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coursework2.databinding.FragmentFilmBinding
import com.example.coursework2.databinding.FragmentHomepageBinding
import com.example.coursework2.popular.PopularAdapter
import com.example.coursework2.popular.PopularInfo
import com.example.coursework2.staff.StaffAdapter
import com.example.coursework2.staff.StaffInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FilmFragment : Fragment() {

    private val args: FilmFragmentArgs by navArgs()
    private val viewModel: MVM by viewModels()

    fun staffInfoRecived(staffInfo: ArrayList<StaffInfo>, binding: FragmentFilmBinding) {
        val adapter = StaffAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(

            context,
            4,
            RecyclerView.HORIZONTAL, false
        )
        adapter.addPoular(staffInfo)
        binding.included1.end.text =
            staffInfo.size.toString() //переопределили вместо текста из xml колличество в шт из бэка

        adapter.onClick = {
            println("999999999999999999999999999999999${it}")
            findNavController().navigate(FilmFragmentDirections.actionFilmfragmentToActorfragment(it))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.getFilmInfo()

        val binding = FragmentFilmBinding.inflate(layoutInflater)

        viewModel.requestStaffInfo(args.premierFilm.kinopoiskId)

        Glide.with(binding.imageViewPoster).load(args.premierFilm.posterUrlPreview)
            .into(binding.imageViewPoster)


        binding.imageButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.recyclerView.setOnClickListener {

        }

        binding.included1.start.text = getString(R.string.Sttaff) // переопределили текст из xml
        binding.included1.end.setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            R.drawable.caret_right,
            0
        ) // добавили в xml картинку стрелки указав ее позицию


        lifecycleScope.launch {
            viewModel.state3.collect {
                CoroutineScope(Dispatchers.Main).launch {
                    if (it != null) {
                        println("rrrrrrrrrrrrrrrrr")
                        staffInfoRecived(it, binding)
                    }
                }
                println("I am collecting message $it")
            }
        }

        lifecycleScope.launch {
            viewModel.state4.collect {
                CoroutineScope(Dispatchers.Main).launch {
                    it.collect {
                        it.firstOrNull {
                            it.filmId == args.premierFilm.kinopoiskId
                        }?.let { filmInTable ->
                            if (filmInTable.beLoved) {
                                binding.icon1.setImageResource(R.drawable.icon_1_active)
                            } else {
                                binding.icon1.setImageResource(R.drawable.icon_1)
                            }
                        }

                    }
                }
            }
        }

        binding.icon1.setOnClickListener{
            viewModel.changeLikeFilm(args.premierFilm.kinopoiskId)
        }

        return binding.root
    }

}
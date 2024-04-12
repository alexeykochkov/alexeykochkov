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
import com.example.coursework2.databinding.FragmentAllPremieresBinding
import com.example.coursework2.premier.PremierFilm
import com.example.coursework2.premier.PremieresAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllPremieres : Fragment() {

    private val viewModel: MVM by viewModels()
    private val args: AllPremieresArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun photosReceived1(premieresFilms: List<PremierFilm>, binding: FragmentAllPremieresBinding) {
        println("pppqqweqwdsadsdf")
        val adapter = PremieresAdapter {
            findNavController().navigate(AllPremieresDirections.actionAllPremieresToFilmFragment(it))
        }
        binding.recyclerView1.adapter = adapter

        binding.recyclerView1.layoutManager = GridLayoutManager(
            context, 2, LinearLayoutManager.VERTICAL, false
        )
        adapter.addPrimieres(premieresFilms)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAllPremieresBinding.inflate(layoutInflater)

//        viewModel.getPremiersFilms() //writer

// передача аргумента для переопределения фрагмента All
        viewModel.requestByKey(args.first) //writer

        lifecycleScope.launch {//reader
            viewModel.state.collect { //state какнал между writer и reader
                CoroutineScope(Dispatchers.Main).launch {
                    if (it != null) {
                        println("rrrrrrrrrrrrrrrrr")
                        photosReceived1(it, binding)
                    }
                }
                println("I am collecting message $it")
            }
        }
        return binding.root
    }

}
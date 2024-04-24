package com.example.coursework2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework2.database.AppDatabase
import com.example.coursework2.databinding.FragmentHomepageBinding
import com.example.coursework2.popular.PopularAdapter
import com.example.coursework2.popular.PopularInfo
import com.example.coursework2.premier.PremierFilm
import com.example.coursework2.premier.PremieresAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomepageFragment : Fragment() {

    private val viewModel: MVM by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.databaseMVM = (activity?.application as AppDatabase).database
        super.onCreate(savedInstanceState)
    }


    fun photosReceived(premieresFilms: List<PremierFilm>, binding: FragmentHomepageBinding) {
        println("pppqqweqwdsadsdf")
        val adapter = PremieresAdapter {
            findNavController().navigate(HomepageFragmentDirections.actionHomepageToFilmFragment(it))
        }
        binding.recyclerView1.adapter = adapter
        binding.recyclerView1.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.HORIZONTAL, false
        )
        adapter.addPrimieres(premieresFilms)
    }

    fun photosReceivedPopular(popularInfo: PopularInfo, binding: FragmentHomepageBinding) {
        val adapter1 = PopularAdapter()
        binding.recyclerView2.adapter = adapter1
        binding.recyclerView2.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.HORIZONTAL, false
        )
        adapter1.addPoular(popularInfo.items)
        adapter1.onClick = {
            println("asasdasdasdew")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomepageBinding.inflate(layoutInflater)

        binding.included1.end.setOnClickListener {
            findNavController().navigate(HomepageFragmentDirections.actionHomepageToAllPremieres(1))
        }

        viewModel.requestPremiersFilms() //writer
        viewModel.requestPopularFilms()

        lifecycleScope.launch {//reader
            viewModel.state.collect { //state какнал между writer и reader
                CoroutineScope(Dispatchers.Main).launch {
                    if (it != null) {
                        println("rrrrrrrrrrrrrrrrr")
                        photosReceived(it, binding)
                    }
                }
                println("I am collecting message $it")
            }
        }

        lifecycleScope.launch {
            viewModel.state2.collect {
                CoroutineScope(Dispatchers.Main).launch {
                    if (it != null) {
                        println("rrrrrrrrrrrrrrrrr")
                        photosReceivedPopular(it, binding)
                    }
                }
                println("I am collecting message $it")
            }
        }

        return binding.root
    }
}
package com.example.movieapptest.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapptest.R
import com.example.movieapptest.databinding.FragmentFavouriteMovieBinding
import com.example.movieapptest.model.local.data.MovieEntity
import com.example.movieapptest.view.adapter.AdapterRoomItemClick
import com.example.movieapptest.view.adapter.MovieAdapter
import com.example.movieapptest.view.adapter.MovieRoomAdapter
import com.example.movieapptest.viewmodel.MovieRoomVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteMovieFragment : Fragment() {

    private lateinit var binding : FragmentFavouriteMovieBinding

    private val viewModelRoom : MovieRoomVM by viewModels()

    private val popularMovieAdapter : MovieRoomAdapter by lazy {

        MovieRoomAdapter(object : AdapterRoomItemClick {
            override fun onItemClicked(item: MovieEntity) {
                val action = FavouriteMovieFragmentDirections.actionFavouriteMovieFragmentToFavouriteDetailFragment(item)
                findNavController().navigate(action)
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentFavouriteMovieBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.rvPopularMovie.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = popularMovieAdapter
        }

        viewModelRoom.readAllMovieData.observe(viewLifecycleOwner) { savedData ->
            popularMovieAdapter.submitList(savedData)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_favourite,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when(item.itemId) {
            R.id.favourite_movie_popular -> {
                item.itemId
            }
            R.id.favourite_movie_detail -> {
                findNavController().navigateUp()
            }
        }
        return true
    }

}
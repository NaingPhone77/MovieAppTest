package com.example.movieapptest.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapptest.R
import com.example.movieapptest.databinding.FragmentPopularMovieBinding
import com.example.movieapptest.helper.AsyncViewResource
import com.example.movieapptest.model.remote.response_data.Result
import com.example.movieapptest.view.adapter.AdapterOnClick
import com.example.movieapptest.view.adapter.MovieAdapter
import com.example.movieapptest.viewmodel.MainMoviePageVM
import com.example.movieapptest.viewmodel.MovieRoomVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMovieFragment : Fragment(){

    private lateinit var binding: FragmentPopularMovieBinding

    private val viewModel : MainMoviePageVM by viewModels()
    private val viewModelRoom : MovieRoomVM by viewModels()

    private val popularMovieAdapter : MovieAdapter by lazy {
        MovieAdapter(object : AdapterOnClick {
            override fun onItemClicked(item: Result) {
                val action = PopularMovieFragmentDirections.actionPopularMovieFragmentToDetailMovieFragment(item)
                findNavController().navigate(action)
            }

        })
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPopularMovieBinding.inflate(layoutInflater,container,false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDefaultDisplayHomeAsUpEnabled(
            false
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPopularMovie.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = popularMovieAdapter
        }

        viewModel.getPopularMovie()
        viewModel.popularMovie.observe(viewLifecycleOwner) {
            when(it) {
                is AsyncViewResource.Error -> {
                    Toast.makeText(context, "Popular Movie found error ${it.errorMessage}", Toast.LENGTH_SHORT).show()
                }

                is AsyncViewResource.Loading -> {
                    Log.d("Popular Movie","Loading???")
                    binding.popularProgressBar.visibility = View.VISIBLE

                }

                is AsyncViewResource.Success -> {
                    Log.d("Popular Movie","Success???")
                    binding.popularProgressBar.visibility = View.GONE
                    popularMovieAdapter.submitList(it.value.results)
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_favourite_logo,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         super.onOptionsItemSelected(item)

        when(item.itemId){
            R.id.favourite_movie_popular -> {
                val action = PopularMovieFragmentDirections.actionPopularMovieFragmentToFavouriteMovieFragment()
                findNavController().navigate(action)
            }
        }
        return true
    }
}
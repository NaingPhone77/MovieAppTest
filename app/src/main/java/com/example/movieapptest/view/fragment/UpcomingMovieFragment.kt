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
import com.example.movieapptest.databinding.FragmentUpcomingMovieBinding
import com.example.movieapptest.helper.AsyncViewResource
import com.example.movieapptest.model.remote.response_data.Result
import com.example.movieapptest.view.adapter.AdapterOnClick
import com.example.movieapptest.view.adapter.MovieAdapter
import com.example.movieapptest.viewmodel.MainMoviePageVM
import dagger.hilt.android.AndroidEntryPoint

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class UpcomingMovieFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingMovieBinding

    private val viewModel: MainMoviePageVM by viewModels()

    private val upcomingMovieAdapter: MovieAdapter by lazy {
        MovieAdapter(object : AdapterOnClick {
            override fun onItemClicked(item: Result) {
//                Toast.makeText(
//                    context,
//                    "${item.original_title ?: item.title} Clicked",
//                    Toast.LENGTH_SHORT
//                ).show()

                val action =
                    UpcomingMovieFragmentDirections.actionUpcomingMovieFragmentToDetailMovieFragment(item)
                findNavController().navigate(action)
            }
        })
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingMovieBinding.inflate(layoutInflater, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDefaultDisplayHomeAsUpEnabled(
            false
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUpcomingMovie.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false  )
            adapter = upcomingMovieAdapter
        }

        viewModel.getUpcomingMovie()
        viewModel.upcomingMovie.observe(viewLifecycleOwner) {
            when (it) {
                is AsyncViewResource.Error -> {
                    Toast.makeText(
                        context,
                        "Upcoming Movie Found Error ${it.errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is AsyncViewResource.Loading -> {
                    Log.d("Upcoming Movie is Loading", "Loading???")
                    binding.rvUpcomingMovie.visibility = View.GONE
                    binding.upcomingProgressBar.visibility = View.VISIBLE
                }
                is AsyncViewResource.Success -> {
                    Log.d("Upcoming Movie is Successful", "Successful")
                    binding.rvUpcomingMovie.visibility = View.VISIBLE
                    binding.upcomingProgressBar.visibility = View.INVISIBLE
                    upcomingMovieAdapter.submitList(it.value.results)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_favourite_logo, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         super.onOptionsItemSelected(item)

        when (item.itemId) {
            R.id.favourite_movie_popular -> {
                val action =
                    UpcomingMovieFragmentDirections.actionUpcomingMovieFragmentToFavouriteMovieFragment()
                findNavController().navigate(action)
            }
        }
        return true
    }


}
package com.example.movieapptest.view.fragment


import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapptest.R
import com.example.movieapptest.databinding.FragmentDetailMovieBinding
import com.example.movieapptest.model.local.data.MovieEntity
import com.example.movieapptest.view.util.Constants.Companion.BASE_IMAGE_URL
import com.example.movieapptest.viewmodel.MovieRoomVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private lateinit var binding : FragmentDetailMovieBinding

    private val args : DetailMovieFragmentArgs by navArgs()

    private val viewModelRoom : MovieRoomVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailMovieBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var movieEntity = MovieEntity(
            0,
            args.detail.title,
            args.detail.original_title,
            args.detail.poster_path,
            args.detail.id.toString(),
            args.detail.vote_average.toString(),
            args.detail.overview,
            args.detail.backdrop_path,
            args.detail.original_language
        )

        Glide.with(binding.root).load(BASE_IMAGE_URL + args.detail.poster_path).into(binding.ivPosterDetailSmall)
        Glide.with(binding.root).load(BASE_IMAGE_URL + args.detail.backdrop_path).into(binding.ivPosterDetailLarge)


        binding.apply {
            tvTitle.text = args.detail.title
            tvRating.text = args.detail.vote_count.toString()
            tvOverview.text = args.detail.overview

        }

        viewModelRoom.readAllMovieData.observe(viewLifecycleOwner) {
            binding.floatingActionButton.setOnClickListener {

                viewModelRoom.addMovie(movieEntity)
                Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show()

            }
        }

    }

}
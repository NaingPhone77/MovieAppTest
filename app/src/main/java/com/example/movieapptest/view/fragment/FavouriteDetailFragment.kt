package com.example.movieapptest.view.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapptest.R
import com.example.movieapptest.databinding.FragmentFavouriteDetailBinding
import com.example.movieapptest.view.util.Constants.Companion.BASE_IMAGE_URL
import com.example.movieapptest.viewmodel.MovieRoomVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteDetailFragment : Fragment(){

    private lateinit var binding : FragmentFavouriteDetailBinding

    private val args : FavouriteDetailFragmentArgs by navArgs()

    private val viewModelRoom : MovieRoomVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavouriteDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.root).load(BASE_IMAGE_URL + args.detail.poster_path).into(binding.ivPosterSmalll)
        Glide.with(binding.root).load(BASE_IMAGE_URL + args.detail.poster_path).into(binding.ivPosterLarge)

        binding.apply {
            tvTitle.text = args.detail.movieTitle
            tvOverview.text = args.detail.overview
            tvRating.text = args.detail.rating
        }

        var favMovie = false

        binding.apply {

            floatingActionButton.setOnClickListener {
                if(favMovie){
                    viewModelRoom.deleteMovie(args.detail)
                    floatingActionButton.imageTintList = ColorStateList.valueOf(requireContext().getColor(R.color.white))
                    favMovie = false
                }
                else{
                    viewModelRoom.addMovie(args.detail)
                    floatingActionButton.imageTintList = ColorStateList.valueOf(requireContext().getColor(R.color.black))
                    favMovie = true
                }
            }

        }
    }

}
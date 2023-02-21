package com.example.movieapptest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapptest.databinding.ItemPopularListBinding
import com.example.movieapptest.model.remote.response_data.detail_movies.MovieDetailResponse
import com.example.movieapptest.model.remote.response_data.Result
import com.example.movieapptest.view.util.Constants.Companion.BASE_IMAGE_URL

class MovieAdapter(private val event : AdapterOnClick)
    : ListAdapter<Result,MovieAdapter.MyViewHolder>(DifferCallBack){


    inner class MyViewHolder(
        private val binding : ItemPopularListBinding,
        private val item : AdapterOnClick
        ) : RecyclerView.ViewHolder(binding.root) {

        private var currentMovieDetailResponse : Result? = null

        init {
            binding.root.setOnClickListener{
                currentMovieDetailResponse?.apply {
                    item.onItemClicked(this)
                }
            }
        }

        fun bind(movieResult: Result) {
            currentMovieDetailResponse = movieResult
            Glide.with(binding.root)
                .load(BASE_IMAGE_URL + movieResult.poster_path)
                .into(binding.ivPosterItem)
            binding.tvMovieName.text =
                movieResult.original_title ?: movieResult.release_date
        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemPopularListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view,event)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}

interface AdapterOnClick {
    fun onItemClicked(item: Result)
}

object DifferCallBack : DiffUtil.ItemCallback<Result>(){
    override fun areItemsTheSame(
        oldItem: Result,
        newItem: Result

    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Result,
        newItem: Result
    ): Boolean {
        return areItemsTheSame(oldItem , newItem)
    }

}
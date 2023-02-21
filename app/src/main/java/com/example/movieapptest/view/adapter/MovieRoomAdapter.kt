package com.example.movieapptest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapptest.databinding.ItemFavouriteListBinding
import com.example.movieapptest.model.local.data.MovieEntity
import com.example.movieapptest.view.util.Constants.Companion.BASE_IMAGE_URL

class MovieRoomAdapter(private val event : AdapterRoomItemClick) :
    ListAdapter<MovieEntity,MovieRoomAdapter.MyViewHolder>(
        DiffRoomCallBack) {

        inner class MyViewHolder(
            private val binding : ItemFavouriteListBinding,
            private val itemClick : AdapterRoomItemClick
        ) : RecyclerView.ViewHolder(binding.root) {

            private var currentMovieRoomData : MovieEntity? = null

            init {
                binding.root.setOnClickListener{
                    currentMovieRoomData?.apply {
                        itemClick.onItemClicked(this)
                    }
                }
            }

            fun bind(movieEntity: MovieEntity) {
                currentMovieRoomData = movieEntity
                Glide.with(binding.root).load(BASE_IMAGE_URL + movieEntity.image)
                    .into(binding.ivPosterItem)

                binding.tvMovieName.text = movieEntity.movieTitle
                binding.tvRatingItem.text = movieEntity.rating
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemFavouriteListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view,event)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}


interface AdapterRoomItemClick {
    fun onItemClicked (item: MovieEntity)
}

object DiffRoomCallBack : DiffUtil.ItemCallback<MovieEntity>(){
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }

}
package com.zaen.moviecatalogue.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.databinding.ItemsMovieBinding
import com.zaen.moviecatalogue.models.Movie
import com.zaen.moviecatalogue.utils.Constants.BASE_IMAGE_URL

abstract class BaseMovieAdapter : RecyclerView.Adapter<BaseMovieAdapter.MovieViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsBaseMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsBaseMovieBinding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.bind(movie)
    }

    open inner class MovieViewHolder(private val binding: ItemsMovieBinding): RecyclerView.ViewHolder(binding.root) {
        open fun bind(movie: Movie) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(BASE_IMAGE_URL+movie.posterUrl)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(ivPoster)
                tvItemTitle.text = movie.title
                itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(movie.id)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}
package com.zaen.moviecatalogue.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.databinding.ItemsMovieBinding
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity
import com.zaen.moviecatalogue.utils.Constants

class TvShowAdapter : PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(differCallback) {

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsBaseMovieBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsBaseMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.bind(tvShow)
    }

    inner class TvShowViewHolder(private val binding: ItemsMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity?) {
            binding.apply {
                tvShow?.apply {
                    Glide.with(itemView.context)
                        .load(Constants.BASE_IMAGE_URL+posterUrl)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(ivPoster)
                    tvItemTitle.text = title
                    itemView.setOnClickListener {
                        onItemClickListener?.let {
                            it(id)
                        }
                    }

                    cvIbShare.visibility = View.VISIBLE
                    ibShare.setOnClickListener {
                        onClickShareListener?.let {
                            it(this)
                        }
                    }
                }
            }
        }
    }

    private var onClickShareListener: ((TvShowEntity) -> Unit)? = null
    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnClickShareListener(listener: (TvShowEntity) -> Unit) {
        onClickShareListener = listener
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}

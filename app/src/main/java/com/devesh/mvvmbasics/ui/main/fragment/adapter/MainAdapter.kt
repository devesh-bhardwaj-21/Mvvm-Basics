package com.devesh.mvvmbasics.ui.main.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devesh.mvvmbasics.data.room.entity.ResultsItem
import com.devesh.mvvmbasics.databinding.MovieReviewItemBinding

class MainAdapter(private val movieReviewList: ArrayList<ResultsItem>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    fun addMovieReviewList(items: List<ResultsItem>) {
        this.movieReviewList.apply {
            clear()
            addAll(items)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.DataViewHolder {
        return DataViewHolder(
            MovieReviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MainAdapter.DataViewHolder,
        position: Int
    ) {
        holder.bind(movieReviewList[position])
    }

    override fun getItemCount(): Int = movieReviewList.size

    inner class DataViewHolder(val binding: MovieReviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultsItem) {
            binding.textViewHeadline.text = item.headline
            binding.textViewTitle.text = item.displayTitle
            binding.textViewByLine.text = "By: ${item.byline}"
            binding.textViewSummary.text = item.summaryShort
            val imageUrl = item.multimedia.src
            Glide.with(binding.imageView.context)
                .load(imageUrl)
                .into(binding.imageView)
        }
    }
}
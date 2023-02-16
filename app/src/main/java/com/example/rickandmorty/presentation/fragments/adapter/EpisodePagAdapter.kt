package com.example.rickandmorty.presentation.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemEpisodeBinding
import com.example.rickandmorty.domain.entity.episode.ResultEntity
import com.example.rickandmorty.utils.BasePagingAdapter

class EpisodePagAdapter :
    PagingDataAdapter<ResultEntity, EpisodePagAdapter.EpisodeViewHolder>(FaqComparator),
    BasePagingAdapter {


    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEpisodeBinding.inflate(inflater, parent, false)
        return EpisodeViewHolder(binding)
    }

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultEntity?) = with(binding) {
            episodeName.text = item?.name
            created.text = item?.created
        }
    }

    object FaqComparator : DiffUtil.ItemCallback<ResultEntity>() {
        override fun areItemsTheSame(
            oldItem: ResultEntity,
            newItem: ResultEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResultEntity,
            newItem: ResultEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}
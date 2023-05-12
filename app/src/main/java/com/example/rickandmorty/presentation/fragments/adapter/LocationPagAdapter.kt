package com.example.rickandmorty.presentation.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemLocationBinding
import com.example.rickandmorty.domain.entity.locations.LocResultEntity
import com.example.rickandmorty.utils.BasePagingAdapter

class LocationPagAdapter  :
    PagingDataAdapter<LocResultEntity, LocationPagAdapter.LocationViewHolder>(FaqComparator),
    BasePagingAdapter {


    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLocationBinding.inflate(inflater, parent, false)
        return LocationViewHolder(binding)
    }

    inner class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LocResultEntity?) = with(binding) {
            name.text = item?.name
            type.text = item?.type
            dimension.text = item?.dimension
            created.text = item?.created

        }
    }

    object FaqComparator : DiffUtil.ItemCallback<LocResultEntity>() {
        override fun areItemsTheSame(
            oldItem: LocResultEntity,
            newItem: LocResultEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LocResultEntity,
            newItem: LocResultEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}
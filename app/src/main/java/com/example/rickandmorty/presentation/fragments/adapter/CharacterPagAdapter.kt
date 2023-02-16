package com.example.rickandmorty.presentation.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.domain.entity.character.Result
import com.example.rickandmorty.presentation.fragments.enums.CharacterStatus
import com.example.rickandmorty.utils.BasePagingAdapter
import com.example.rickandmorty.utils.setImage

class CharacterPagAdapter:
    PagingDataAdapter<Result, CharacterPagAdapter.CharacterViewHolder>(FaqComparator),
    BasePagingAdapter {


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding)
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Result?) = with(binding) {
                item?.image?.let { imageItemCharacter.setImage(it) }
                textItemCharacterName.text = item?.name
                gender.text = item?.gender
                created.text = item?.created
                textItemCharacterLastKnownLocationData.text = item?.location?.name
                textItemCharacterStatusAndSpecies.text = imageItemCharacterStatus.context.resources.getString(
                    R.string.hyphen, item?.status, item?.species
                )
                item?.status?.let { setupCharacterStatus(it) }
            }

        private fun setupCharacterStatus(status: String) = with(binding) {
            when (status) {
                CharacterStatus.ALIVE.status -> {
                    imageItemCharacterStatus.setImageResource(CharacterStatus.ALIVE.image)
                }
                CharacterStatus.DEAD.status -> {
                    imageItemCharacterStatus.setImageResource(CharacterStatus.DEAD.image)
                }
                CharacterStatus.UNKNOWN.status -> {
                    imageItemCharacterStatus.setImageResource(CharacterStatus.UNKNOWN.image)
                }
            }
        }

    }

    object FaqComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem == newItem
        }
    }


}
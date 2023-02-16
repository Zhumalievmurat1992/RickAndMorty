package com.example.rickandmorty.presentation.fragments.enums

import androidx.annotation.DrawableRes
import com.example.rickandmorty.R

enum class CharacterStatus(
    val status: String,
    @DrawableRes val image: Int
) {
    ALIVE("Alive", R.drawable.character_status_alive),
    DEAD("Dead", R.drawable.character_status_dead),
    UNKNOWN("unknown", R.drawable.character_status_unknown)
}
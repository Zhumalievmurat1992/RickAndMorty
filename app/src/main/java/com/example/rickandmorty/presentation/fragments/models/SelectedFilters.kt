package com.example.rickandmorty.presentation.fragments.models

import java.io.Serializable

data class SelectedFilters(
    var status: String? = null,
    var species: String? = null,
    var gender: String? = null
) : Serializable

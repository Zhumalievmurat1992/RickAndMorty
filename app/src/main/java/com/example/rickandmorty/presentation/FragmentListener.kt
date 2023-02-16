package com.example.rickandmorty.presentation

import com.example.rickandmorty.utils.safeFlowGather
import kotlinx.coroutines.flow.collectLatest

interface FragmentListener {
    fun getListeners()
}
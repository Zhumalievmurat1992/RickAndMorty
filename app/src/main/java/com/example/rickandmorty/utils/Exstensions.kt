package com.example.rickandmorty.utils

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rickandmorty.presentation.fragments.adapter.CharacterPagAdapter
import kotlinx.coroutines.launch


fun NavController.navigateSafely(@IdRes actionId: Int) {
    currentDestination?.getAction(actionId)?.let { navigate(actionId) }
}
fun ImageView.setImage(uri: String) {
    Glide.with(this)
        .load(uri)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun Fragment.safeFlowGather(action: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            action()
        }
    }
}


fun Fragment.loadListener(
    progressBar: ProgressBar,
    recMain: RecyclerView
) {
    val mAdapter = CharacterPagAdapter()


    mAdapter.addLoadStateListener { state ->
        recMain.isVisible = state.refresh != LoadState.Loading
        progressBar.isVisible = state.refresh == LoadState.Loading

        val errorState = when {
            state.append is LoadState.Error -> state.append as LoadState.Error
            state.prepend is LoadState.Error -> state.prepend as LoadState.Error
            state.refresh is LoadState.Error -> state.refresh as LoadState.Error
            else -> null
        }
        errorState?.let {
            Toast.makeText(requireActivity(), it.error.toString(), Toast.LENGTH_LONG)
                .show()
        }
    }
}




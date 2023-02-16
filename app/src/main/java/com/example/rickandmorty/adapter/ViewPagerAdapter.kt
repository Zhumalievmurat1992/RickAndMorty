package com.example.rickandmorty.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rickandmorty.presentation.fragments.CharacterFragment
import com.example.rickandmorty.presentation.fragments.EpisodeFragment
//import com.example.rickandmorty.presentation.fragments.LocationFragment
import com.example.rickandmorty.presentation.fragments.FilterFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                CharacterFragment()
            }
            1 -> {
                EpisodeFragment()
            }
//            2 -> {
//                LocationFragment()
//            }
            else -> {
                Fragment()
            }
        }
    }
}
package com.example.rickandmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.rickandmorty.databinding.FragmentContainerBinding
import com.example.rickandmorty.viewPagerAdapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ContainerFragment : Fragment() {

    private lateinit var binding: FragmentContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContainerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        binding.btnFilter.setOnClickListener {
            val action =
                ContainerFragmentDirections.actionContainerFragmentToFilterFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun initViewPager() {

        val tabLayout: TabLayout = binding.tabLayout
        val viewPager2: ViewPager2 = binding.viewPager2
        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Character"
                }
                1 -> {
                    tab.text = "Episode"
                }
                2 -> {
                    tab.text = "Location"
                }
            }
        }.attach()
    }


}
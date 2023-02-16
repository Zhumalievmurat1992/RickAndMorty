package com.example.rickandmorty.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.rickandmorty.R
import com.example.rickandmorty.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.rickandmorty.presentation.fragments.viewModel.CharacterViewModel
import com.example.rickandmorty.presentation.fragments.CharacterFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.rickandmorty.databinding.ActivityMainBinding
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
    }
    private fun initViewPager() {
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager2)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

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
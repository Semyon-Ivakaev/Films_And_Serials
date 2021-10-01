package com.example.filmsandserials.fragments.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.filmsandserials.fragments.viewpager.tabs.MovieFragmentTabs
import com.example.filmsandserials.fragments.viewpager.tabs.SerialFragmentTabs
import com.example.filmsandserials.viewmodels.SearchViewModel

class ViewPagerAdapter(fa: FragmentActivity, private val searchViewModel: SearchViewModel?): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragmentTabs(searchViewModel)
            else -> SerialFragmentTabs(searchViewModel)
        }
    }
}
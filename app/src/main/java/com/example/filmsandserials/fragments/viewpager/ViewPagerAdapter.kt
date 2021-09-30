package com.example.filmsandserials.fragments.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.filmsandserials.fragments.viewpager.tabs.MovieFragmentTabs
import com.example.filmsandserials.fragments.viewpager.tabs.SerialFragmentTabs
import com.example.filmsandserials.viewmodels.SearchViewModel

class ViewPagerAdapter(fm: FragmentManager, val searchViewModel: SearchViewModel?): FragmentPagerAdapter(fm) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragmentTabs(searchViewModel)
            else -> SerialFragmentTabs(searchViewModel)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Search Movie"
            else -> "Search Serial"
        }
    }
}
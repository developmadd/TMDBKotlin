package com.madd.madd.tmdbkotlin.Utilities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var fragmentList = ArrayList<Fragment>()
    private var fragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment,title: String){
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}
package com.example.randomnamesproj.ui.main.ui.main


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.randomnamesproj.ui.description.PersonDescriptionActivity.Companion.ARG_FEMALE
import com.example.randomnamesproj.ui.description.PersonDescriptionActivity.Companion.ARG_MALE

import com.example.randomnamesproj.ui.main.ui.female.RandomNameFragment.Companion.newInstance


class ViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    init {
        addFragment(newInstance(ARG_MALE), "Male")

        addFragment(newInstance(ARG_FEMALE), "Female")
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    private fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}


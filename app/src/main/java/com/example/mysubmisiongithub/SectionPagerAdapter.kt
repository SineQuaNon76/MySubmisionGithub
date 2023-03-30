package com.example.mysubmisiongithub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    var user: String = ""
    override fun createFragment(position: Int): Fragment {
        val fragment = FollowersFragment()
        fragment.arguments = Bundle().apply {
            putString(FollowersFragment.SECTION_USERNAME, user)
            putInt(FollowersFragment.SECTION_POSITION, position+1)
        }
        return fragment
    }


    override fun getItemCount(): Int {
        return 2
    }



}
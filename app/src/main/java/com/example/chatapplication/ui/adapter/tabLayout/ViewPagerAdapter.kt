package com.example.chatapplication.ui.adapter.tabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chatapplication.ui.chat.ChatFragment
import com.example.chatapplication.ui.feeds.FeedFragment
import com.example.chatapplication.ui.navigation.CHAT
import com.example.chatapplication.ui.navigation.FEED
import com.example.chatapplication.ui.navigation.SETTINGS
import com.example.chatapplication.ui.settings.SettingsFragment
import java.util.ArrayList

class ViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    private val identifiers = ArrayList<String>()
    override fun getItemCount(): Int {
        return identifiers.size
    }

    override fun createFragment(position: Int): Fragment {

        return when(identifiers[position]){
            CHAT -> ChatFragment()
            SETTINGS -> SettingsFragment()
            FEED -> FeedFragment()
            else-> Fragment()
        }
    }

    fun addIdentifiers(identifiers: List<String>) {
        this.identifiers.clear()
        this.identifiers.addAll(identifiers)
    }
}
package com.example.chatapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatapplication.databinding.ActivityMainBinding
import com.example.chatapplication.databinding.ActivitySocialBinding
import com.example.chatapplication.ui.adapter.tabLayout.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SocialActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySocialBinding
    var pagerAdapter: ViewPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_social)
        binding = ActivitySocialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        pagerAdapter = ViewPagerAdapter(this)
        pagerAdapter?.apply {
            addIdentifiers(
                listOf(
                    "CHAT",
                    "SETTINGS",
                    "FEED"
                )
            )
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
           tab.text = when (position) {
                0 -> "Tab One"
                1 -> "Tab Two"
                else -> "Tab One"
            }
        }.attach()



    }
}
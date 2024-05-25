package com.exorium.myremote.ui.main

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.exorium.myremote.R
import com.exorium.myremote.databinding.ActivityMainBinding
import com.exorium.myremote.ui.adapter.ViewPagerAdapter
import com.exorium.myremote.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    override fun initView() {
        initBottomNavigation()
        initViewPager()
    }

    private fun initBottomNavigation() {
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_cast-> {
                    binding.viewPager.currentItem = 0
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_remote -> {
                    binding.viewPager.currentItem = 1
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_setting -> {
                    binding.viewPager.currentItem = 2
                    return@setOnItemSelectedListener true
                }
            }
            true
        }
    }

    private fun initViewPager() {
        val fragmentList = arrayListOf(CastFragment(), RemoteFragment(), SettingFragment())
        val adapter = ViewPagerAdapter(fragmentList, supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter
//        binding.viewPager.isUserInputEnabled = false

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNavView.menu.getItem(position).isChecked = true
            }
        })

    }
}
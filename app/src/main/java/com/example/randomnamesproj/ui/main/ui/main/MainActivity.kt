package com.example.randomnamesproj.ui.main.ui.main

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.example.randomnamesproj.R
import com.example.randomnamesproj.databinding.ActivityMainBinding

open class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply {
            viewPager.adapter = adapter
            tabs.setupWithViewPager(viewPager)
        }


    }


}

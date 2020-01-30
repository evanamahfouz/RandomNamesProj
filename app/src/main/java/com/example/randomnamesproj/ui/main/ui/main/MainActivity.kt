package com.example.randomnamesproj.ui.main.ui.main

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.example.randomnamesproj.R
//import com.example.randomnamesproj.data.db.RandomNameDataBase
import com.example.randomnamesproj.ui.main.ui.female.FemaleFragment
import com.example.randomnamesproj.ui.main.ui.male.MaleFragment
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity() {

    //  private lateinit var dB: RandomNameDataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main)


        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MaleFragment(), "Male")

        adapter.addFragment(FemaleFragment(), "Female")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }


}

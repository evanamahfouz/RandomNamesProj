package com.example.randomnamesproj.ui.main.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomnamesproj.R
import com.example.randomnamesproj.ui.main.ui.female.FemaleFragment

class FemaleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.female_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FemaleFragment.newInstance())
                .commitNow()
        }
    }

}

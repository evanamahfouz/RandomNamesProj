package com.example.randomnamesproj.ui.description

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.randomnamesproj.R
import com.example.randomnamesproj.databinding.ActivityBookDecripBinding
import kotlinx.android.synthetic.main.activity_book_decrip.*

class BookDescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDecripBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityBookDecripBinding>(
            this,
            R.layout.activity_book_decrip
        ).apply {
            description = intent.getStringExtra(ARG_DESC)
        }

        intent.getStringExtra(ARG_DESC) ?: let {
            text_desc?.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
            text_desc?.setPadding(32, 0, 0, 0)
        }
    }

    companion object {
        const val ARG_DESC = "Description"

    }
}

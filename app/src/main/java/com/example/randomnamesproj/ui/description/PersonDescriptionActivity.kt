package com.example.randomnamesproj.ui.description

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.databinding.PersonDecripBinding
import androidx.core.app.NavUtils


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PersonDescriptionActivity : AppCompatActivity() {

    private lateinit var binding: PersonDecripBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<PersonDecripBinding>(
            this,
            com.example.randomnamesproj.R.layout.person_decrip
        ).apply {
            val paracablreObj = intent.getParcelableExtra<RandomName>(ARG_NAME)
            fullname.text =
                paracablreObj.name + " " + paracablreObj.surname
            region1.text = paracablreObj.region
            if (paracablreObj.gender == getString(com.example.randomnamesproj.R.string.Male)) {
                imageView.setImageResource(com.example.randomnamesproj.R.drawable.family_son)
            } else {
                imageView.setImageResource(com.example.randomnamesproj.R.drawable.family_daughter)

            }


        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ARG_NAME = "name"


    }
}

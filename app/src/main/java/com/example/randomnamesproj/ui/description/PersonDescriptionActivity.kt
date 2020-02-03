package com.example.randomnamesproj.ui.description

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.randomnamesproj.R
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.databinding.PersonDecripBinding

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PersonDescriptionActivity : AppCompatActivity() {

    private lateinit var binding: PersonDecripBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<PersonDecripBinding>(
            this,
            R.layout.person_decrip
        ).apply {
            val paracablreObj = intent.getParcelableExtra<RandomName>(ARG_NAME)
            fullname.text =
                paracablreObj.name + " " + paracablreObj.surname
            region1.text = paracablreObj.region
            if (paracablreObj.gender == getString(R.string.Male)) {
                imageView.setImageResource(R.drawable.family_son)
            } else {
                imageView.setImageResource(R.drawable.family_daughter)

            }


        }

    }

    companion object {
        const val ARG_NAME = "name"


    }
}

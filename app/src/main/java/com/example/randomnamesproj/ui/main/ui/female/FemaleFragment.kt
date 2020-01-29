package com.example.randomnamesproj.ui.main.ui.female

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.randomnamesproj.R

class FemaleFragment : Fragment() {

    companion object {
        fun newInstance() = FemaleFragment()
    }

    private lateinit var viewModel: FemaleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.female_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
                viewModel = ViewModelProviders.of(this).get(FemaleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
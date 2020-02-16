package com.example.randomnamesproj.ui.main.ui.female

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.randomnamesproj.ui.main.ui.main.RandomNameAdapter
import kotlinx.android.synthetic.main.content_recycle_name.*
import androidx.recyclerview.widget.DividerItemDecoration
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import com.example.randomnamesproj.databinding.ContentRecycleNameBinding
import com.example.randomnamesproj.ui.description.PersonDescriptionActivity
import com.example.randomnamesproj.ui.description.PersonDescriptionActivity.Companion.ARG_NAME
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class RandomNameFragment : Fragment() {

    private lateinit var viewModel: RandomNameViewModel
    private lateinit var adapter: RandomNameAdapter
    private lateinit var binding: ContentRecycleNameBinding

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                com.example.randomnamesproj.R.layout.content_recycle_name,
                container,
                false
            )


        val gender = arguments?.getString("gender")

        viewModel = ViewModelProviders.of(this, factory).get(RandomNameViewModel::class.java)

        viewModel.getPost(gender.orEmpty())


        // in content do not change the layout size of the RecyclerView
        adapter = RandomNameAdapter {
            val intent = Intent(context, PersonDescriptionActivity::class.java)
                .putExtra(ARG_NAME, it)

            startActivity(intent)
        }

        with(binding.myRecyclerView) {
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        }

        binding.myRecyclerView.adapter = adapter

        //Dividers
        val itemDecor = DividerItemDecoration(context, HORIZONTAL)
        binding.myRecyclerView.addItemDecoration(itemDecor)


        viewModel.mutableList.observe(viewLifecycleOwner, Observer {

            adapter.submitList(it)
            prof.visibility = View.GONE
        })

        viewModel.mutableError.observe(this, Observer { errorLabel ->

            //database
            if (errorLabel.isNotEmpty()) {


                prof.visibility = View.GONE

                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show()

            }
        })
        return binding.root

    }


    companion object {
        fun newInstance(gender: String): RandomNameFragment {
            return RandomNameFragment().apply {
                arguments = Bundle().apply {
                    putString("gender", gender)
                }
            }
        }
    }
}
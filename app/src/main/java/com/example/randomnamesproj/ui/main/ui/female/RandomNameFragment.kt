package com.example.randomnamesproj.ui.main.ui.female

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.randomnamesproj.ui.main.ui.main.RandomNameAdapter
import kotlinx.android.synthetic.main.content_recycle_name.*
import androidx.recyclerview.widget.DividerItemDecoration
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import com.example.randomnamesproj.data.model.RandomName
import com.example.randomnamesproj.databinding.ContentRecycleNameBinding
import com.example.randomnamesproj.ui.description.PersonDescriptionActivity
import com.example.randomnamesproj.ui.description.PersonDescriptionActivity.Companion.ARG_NAME


class RandomNameFragment : Fragment(), RandomNameView {

    private lateinit var adapter: RandomNameAdapter
    private lateinit var binding: ContentRecycleNameBinding
    private lateinit var presenter: RandomNamePresenter
    private lateinit var gender: String
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


        //MVP
        gender = arguments?.getString("gender")!!

        presenter = RandomNamePresenter(this)
        presenter.getPost(gender)

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



        return binding.root

    }

    override fun onGetPost(data: List<RandomName>) {


        if (data.isNotEmpty()) {
            adapter.submitList(data)
            prof.visibility = View.GONE
        } else {
            prof.visibility = View.GONE

            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show()
        }
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
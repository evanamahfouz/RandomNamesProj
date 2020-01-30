package com.example.randomnamesproj.ui.main.ui.male

import android.graphics.drawable.ClipDrawable
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.randomnamesproj.R
import com.example.randomnamesproj.data.db.RandomNameDataBase
import com.example.randomnamesproj.databinding.ContentrecyclenameBinding
import com.example.randomnamesproj.ui.main.ui.main.MyAdapter
import kotlinx.android.synthetic.main.contentrecyclename.*

class MaleFragment : Fragment() {

    companion object {
        fun newInstance() = MaleFragment()
    }

    private lateinit var viewModel: MaleViewModel
    private lateinit var adapter: MyAdapter
    private lateinit var binding: ContentrecyclenameBinding
    private lateinit var dB: RandomNameDataBase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.contentrecyclename, container, false)



        viewModel = ViewModelProviders.of(this).get(MaleViewModel::class.java)

        dB = RandomNameDataBase.getInstance()


        // in content do not change the layout size of the RecyclerView
        adapter = MyAdapter(context!!)

        with(binding.myRecyclerView) {
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        }

        binding.myRecyclerView.adapter = adapter

        //Dividers
        val itemDecor = DividerItemDecoration(context, ClipDrawable.HORIZONTAL)
        binding.myRecyclerView.addItemDecoration(itemDecor)

        viewModel.mutableList.observe(this, Observer {
            Log.v(
                "helloFromMain",
                it.size.toString() + " " + it[0].name + it[1].surname + it[2].region
            )
            adapter.submitList(it)
            prof.visibility = View.GONE
        })

        viewModel.mutableError.observe(this, Observer { errorLabel ->

            //database
            if (errorLabel.isNotEmpty()) {


                Log.v("OnFailure", "Something Went Wrong")
                prof.visibility = View.GONE

                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show()

            }
        })
        return binding.root

    }
}


package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.adapter.UserInfoPagedController
import com.example.myapplication.databinding.FragmentEpoxyBinding
import com.example.myapplication.extension.NonNullObserver

class EpoxyFragment : BaseFragment() {
    private val userInfoController by lazy { UserInfoPagedController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentEpoxyBinding>(
            inflater,
            R.layout.fragment_epoxy,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.btnSearch.setOnClickListener { viewModel.onSearch() }
        binding.recyclerView.apply {
            setController(userInfoController)
            addItemDecoration(decoration)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.pagedInfo.observe(this, NonNullObserver {
            userInfoController.submitList(it)
        })

        userInfoController.clickEvent.observe(this, NonNullObserver {
            Toast.makeText(activity, "選擇${it.name}", Toast.LENGTH_SHORT).show()
        })
    }
}
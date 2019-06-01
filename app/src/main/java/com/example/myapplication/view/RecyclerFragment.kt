package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.UserInfoPagedAdapter
import com.example.myapplication.databinding.FragmentRecyclerviewBinding
import com.example.myapplication.extension.NonNullObserver

class RecyclerFragment : BaseFragment() {
    private val userInfoAdapter by lazy { UserInfoPagedAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentRecyclerviewBinding>(
            inflater,
            R.layout.fragment_recyclerview,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.btnSearch.setOnClickListener { viewModel.onSearch() }
        binding.recyclerView.apply {
            adapter = userInfoAdapter
            layoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)

            addItemDecoration(decoration)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.pagedInfo.observe(this, NonNullObserver { userInfoAdapter.submitList(it) })

        userInfoAdapter.clickEvent.observe(this, NonNullObserver { showToast("選擇${it.name}") })
    }
}
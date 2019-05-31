package com.example.myapplication.view

import android.app.AlertDialog
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewLoadingBinding
import com.example.myapplication.extension.NonNullObserver
import com.example.myapplication.viewmodel.FragmentViewModel
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseFragment : Fragment() {
    protected val viewModel by viewModel<FragmentViewModel>()

    protected val decoration = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val margin = resources.getDimensionPixelOffset(R.dimen.item_margin)
            outRect.top = if (position == 0) margin else 0
            outRect.bottom = if (position == state.itemCount - 1) 0 else margin
            outRect.left = margin
            outRect.right = margin
        }
    }

    private val loadingDialog by lazy { loadingDialog() }

    private fun loadingDialog(): AlertDialog {
        val builder = AlertDialog.Builder(activity, R.style.CustomDialog)
        val binding =
            DataBindingUtil.inflate<ViewLoadingBinding>(
                LayoutInflater.from(activity),
                R.layout.view_loading,
                null,
                false
            )
        builder.setView(binding.root)
        builder.setCancelable(false)
        return builder.create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.progressBarVisibility.observe(this, NonNullObserver {
            if (it) loadingDialog.show() else loadingDialog.dismiss()
        })
    }
}
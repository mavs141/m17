package com.example.myapplication.view

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.extension.NonNullObserver
import com.example.myapplication.viewmodel.ActivityViewModel
import com.example.myapplication.viewmodel.FragmentViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseFragment : Fragment() {
    protected val viewModel by viewModel<FragmentViewModel>()
    private val activityViewModel by sharedViewModel<ActivityViewModel>()

    private var toast: Toast? = null

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

    protected fun showToast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT).apply {
            show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.progressBarVisibility.observe(this, NonNullObserver {
            activityViewModel.setProgressbarVisibility(it)
        })
    }
}
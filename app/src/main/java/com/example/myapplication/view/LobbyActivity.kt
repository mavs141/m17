package com.example.myapplication.view


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLobbyBinding
import com.example.myapplication.databinding.ViewLoadingBinding
import com.example.myapplication.extension.NonNullObserver
import com.example.myapplication.viewmodel.ActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LobbyActivity : AppCompatActivity() {
    private val viewModel by viewModel<ActivityViewModel>()

    private val loadingDialog by lazy { loadingDialog() }

    private fun loadingDialog(): AlertDialog {
        val builder = AlertDialog.Builder(this, R.style.CustomDialog)
        val binding =
            DataBindingUtil.inflate<ViewLoadingBinding>(
                LayoutInflater.from(this),
                R.layout.view_loading,
                null,
                false
            )
        builder.setView(binding.root)
        builder.setCancelable(false)
        return builder.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityLobbyBinding>(this, R.layout.activity_lobby)

        binding.lifecycleOwner = this

        binding.btnGoToRecyclerview.setOnClickListener { changeFragment(RecyclerFragment()) }

        binding.btnGoToEpoxy.setOnClickListener { changeFragment(EpoxyFragment()) }

        viewModel.progressBarVisibility.observe(this, NonNullObserver {
            if (it) loadingDialog.show() else loadingDialog.dismiss()
        })

    }

    private fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        if (cleanStack) clearBackStack()

        supportFragmentManager.beginTransaction().apply {
            this.replace(R.id.content_fragment, f)
            this.addToBackStack(null)
            this.commit()
        }
    }

    private fun clearBackStack() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            val first = supportFragmentManager.getBackStackEntryAt(0)
            supportFragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}

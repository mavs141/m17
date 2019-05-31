package com.example.myapplication.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLobbyBinding

class LobbyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityLobbyBinding>(this, R.layout.activity_lobby)

        binding.lifecycleOwner = this

        binding.btnGoToRecyclerview.setOnClickListener { changeFragment(RecyclerFragment()) }

        binding.btnGoToEpoxy.setOnClickListener { changeFragment(EpoxyFragment()) }

    }

    private fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        if(cleanStack) clearBackStack()

        supportFragmentManager.beginTransaction().apply {
            this.replace(R.id.content_fragment,f)
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

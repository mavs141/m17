package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.SingleLiveEvent
import com.example.myapplication.data.UserInfo
import com.example.myapplication.databinding.ItemsUserInfoBinding


class UserInfoPagedAdapter : PagedListAdapter<UserInfo, UserInfoPagedAdapter.PagedUserInfoViewHolder>(UserInfoDiff) {

    private val _clickEvent = SingleLiveEvent<UserInfo>()
    val clickEvent: LiveData<UserInfo> get() = _clickEvent

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagedUserInfoViewHolder {
        val binding = DataBindingUtil.inflate<ItemsUserInfoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.items_user_info,
            parent,
            false
        )
        return PagedUserInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagedUserInfoViewHolder, position: Int) {
        holder.onBind(getItem(position)!!, _clickEvent)
    }

    class PagedUserInfoViewHolder(private val binding: ItemsUserInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserInfo, _clickEvent: SingleLiveEvent<UserInfo>) {
            binding.userInfo = data
            binding.setClickEvent { _clickEvent.postValue(data) }
        }

    }

    companion object UserInfoDiff : DiffUtil.ItemCallback<UserInfo>() {
        override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo) = oldItem == newItem
    }
}
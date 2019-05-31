package com.example.myapplication.adapter

import androidx.lifecycle.LiveData
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.myapplication.SingleLiveEvent
import com.example.myapplication.UserInfoBindingModel_
import com.example.myapplication.data.UserInfo

class UserInfoPagedController :
    PagedListEpoxyController<UserInfo>(modelBuildingHandler = EpoxyAsyncUtil.getAsyncBackgroundHandler()) {

    private val _clickEvent = SingleLiveEvent<UserInfo>()
    val clickEvent: LiveData<UserInfo> get() = _clickEvent

    override fun buildItemModel(currentPosition: Int, item: UserInfo?): EpoxyModel<*> {
        return UserInfoBindingModel_()
            .id("user info $currentPosition")
            .clickEvent { _ -> _clickEvent.postValue(item) }
            .userInfo(item)
    }


}
package com.ifenghui.home.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.lifecycle.ViewModel
import com.ifenghui.apilibrary.api.entity.Story
import com.ifenghui.commonlibrary.base.ui.activity.BaseActivity
import com.ifenghui.commonlibrary.utils.RecyclerViewManagerUtils
import com.ifenghui.home.BR
import com.ifenghui.home.R
import com.ifenghui.home.databinding.ActivityTouchstorysLayoutBinding
import com.ifenghui.home.mvvm.model.TouchStorysModel
import com.ifenghui.home.mvvm.viewmodel.TouchStorysViewModel
import com.ifenghui.home.ui.adapter.TouchStorysAdapter

class TouchStorysActivity : BaseActivity<ActivityTouchstorysLayoutBinding, TouchStorysViewModel>() {
    override fun <T : ViewModel> createViewModel(modelClass: Class<T>): T {
        return mActivity()?.application?.let { TouchStorysViewModel(it, TouchStorysModel(mActivity()?.application!!)) } as T
    }

    override fun onBindLayout(): Int {
        return R.layout.activity_touchstorys_layout
    }

    override fun onBindViewModel(): Class<TouchStorysViewModel> {
        return TouchStorysViewModel::class.java
    }

    override fun onBindVariableId(): Int {
        return BR.touchViewModel
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateDelay(bundle: Bundle?) {

        resetToolBarTitle("摸一摸")
        RecyclerViewManagerUtils.setLinearLayoutManager(mBinding?.recyclerView, mActivity(), true)
        var adapter=TouchStorysAdapter(mActivity())
        mBinding?.recyclerView?.adapter=adapter
        adapter?.notifyDataSetChanged()
        mBinding?.recyclerView?.scrollToPosition(Int.MAX_VALUE-1)
        mBinding?.recyclerView?.setOnTouchListener { _, _ -> true }
        getPredata()
    }

    private fun getPredata(){
        try {
            val bundle = intent.extras
            val serializable = bundle?.getSerializable("list_data")
            mViewModel?.setListData(serializable as ArrayList<Story> )
        }catch (e:Exception){}
    }
}
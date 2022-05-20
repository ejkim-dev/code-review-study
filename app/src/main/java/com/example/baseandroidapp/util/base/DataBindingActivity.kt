package com.example.baseandroidapp.util.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class DataBindingActivity<VB : ViewDataBinding> :
    BaseActivity() {

    @LayoutRes
    abstract fun getLayoutId(): Int                         //xml LayoutId를 넣는다
    abstract fun viewMaker(savedInstanceState: Bundle?)     //click event를 담는 그릇

    protected lateinit var binding: VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = contentViewDataBinding(getLayoutId())
        viewMaker(savedInstanceState)


    }


    private fun <VB : ViewDataBinding> contentViewDataBinding(
        layoutId: Int,
        lifecycleOwner: Boolean = true
    ): VB {
        val binding = DataBindingUtil.setContentView<VB>(this@DataBindingActivity, layoutId)
        if (lifecycleOwner) {
            binding.lifecycleOwner = this
        }

        return binding
    }



}
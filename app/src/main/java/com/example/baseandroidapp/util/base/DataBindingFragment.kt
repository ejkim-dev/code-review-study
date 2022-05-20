package com.example.baseandroidapp.util.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class DataBindingFragment<VB : ViewDataBinding> : BaseFragment() {
    @LayoutRes
    abstract fun getLayoutId(): Int                         //xml LahyoutId를 넣는다
    abstract fun viewMaker(savedInstanceState: Bundle?)     //click event를 담는 그릇

    protected lateinit var binding: VB


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateDataBinding(inflater, getLayoutId(), container)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewMaker(savedInstanceState)
    }

    fun isInitializedBinding(): Boolean {
        return ::binding.isInitialized
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onStop() {
        super.onStop()
    }

    private fun <VB : ViewDataBinding> inflateDataBinding(
        inflater: LayoutInflater,
        layoutId: Int,
        parent: ViewGroup?,
        attachToParent: Boolean = false,
        lifecycleOwner: Boolean = true
    ): VB {
        val binding = DataBindingUtil.inflate<VB>(inflater, layoutId, parent, attachToParent)
        if (lifecycleOwner) {
            binding.lifecycleOwner = viewLifecycleOwner
        }

        return binding
    }
}
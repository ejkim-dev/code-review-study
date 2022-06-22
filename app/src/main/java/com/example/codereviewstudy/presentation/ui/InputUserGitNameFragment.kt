package com.example.codereviewstudy.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.codereviewstudy.databinding.InputUserGitNameFragmentBinding
import com.example.codereviewstudy.presentation.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputUserGitNameFragment : BindingFragment<InputUserGitNameFragmentBinding, InputUserGitNameViewModel >() {

    companion object {
        fun newInstance() = InputUserGitNameFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> InputUserGitNameFragmentBinding
        get() = InputUserGitNameFragmentBinding::inflate


    override fun initView() {
        binding.apply {
            btnNextOk.setOnClickListener {
                val username = etUserName.text.toString()
                viewmodel.getUserInfo(username)
            }
        }

    }


}
package com.example.codereviewstudy.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.codereviewstudy.databinding.InputUserGitNameFragmentBinding
import com.example.codereviewstudy.presentation.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputUserGitNameFragment : BindingFragment<InputUserGitNameFragmentBinding>() {

    companion object {
        fun newInstance() = InputUserGitNameFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> InputUserGitNameFragmentBinding
        get() = InputUserGitNameFragmentBinding::inflate


    private lateinit var userGitNameViewModel: InputUserGitNameViewModel


    override fun initView() {

    }


}
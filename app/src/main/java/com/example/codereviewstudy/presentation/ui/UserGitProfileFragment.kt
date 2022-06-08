package com.example.codereviewstudy.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.codereviewstudy.databinding.UserGitProfileFragmentBinding
import com.example.codereviewstudy.presentation.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserGitProfileFragment : BindingFragment<UserGitProfileFragmentBinding>() {

    companion object {
        fun newInstance() = UserGitProfileFragment()
    }

    private lateinit var viewModel: UserGitProfileViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> UserGitProfileFragmentBinding
        get() = UserGitProfileFragmentBinding::inflate

    override fun initView() {
        binding.apply {
            btnNavUp.setOnClickListener {
                navigateUp()
            }
        }

    }


}
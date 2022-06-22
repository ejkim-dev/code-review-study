package com.example.codereviewstudy.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.codereviewstudy.presentation.ui.MainActivity
import javax.inject.Inject

abstract class BindingFragment<B : ViewBinding, ViewModel : BaseViewModel> : Fragment() {

    lateinit var binding : B
    abstract val bindingInflater : (LayoutInflater,ViewGroup?, Boolean) -> B

    @Inject
    lateinit var viewmodel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater.invoke(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        initView()
    }

    abstract fun initView()

    fun getMainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

    private fun getNavOptions(): NavOptions.Builder {
        return NavOptions.Builder()
    }

    fun navigate(
        action: NavDirections,
        destinationId: Int? = null,
        isPopupTo: Boolean = false,
        isInclusive: Boolean = true,
        navOptions: NavOptions.Builder? = getMainActivity().getNavOptions()
    ) {
        if (isPopupTo) {
            destinationId?.let {
                navOptions?.setPopUpTo(it, isInclusive)
            }

        }
        findNavController().navigate(action, navOptions?.build())

    }
    fun navigateUp() {
        findNavController().navigateUp()
    }



}
package com.devhoony.baseandroidapp.sample.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.devhoony.baseandroidapp.R
import com.devhoony.baseandroidapp.databinding.FragmentHomeBinding
import com.devhoony.baseandroidapp.util.base.BaseFragment


class HomeFragment : BaseFragment() {

//    lateinit var btnDetail : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        binding.btnDetail.setOnClickListener {
            Toast.makeText(requireContext(), "detail", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.home_detail_dest)
//            Navigation.createNavigateOnClickListener(R.id.next_action, null)

//            Navigation.findNavController(view).navigate(R.id.next_action)

//            val numberArg = 1
//            val action = HomeFragmentDirections.nextAction(numberArg)
//            findNavController().navigate(action)
        }
    }


}
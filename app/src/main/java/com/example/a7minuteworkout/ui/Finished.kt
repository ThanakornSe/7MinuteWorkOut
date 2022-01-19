package com.example.a7minuteworkout.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.databinding.FragmentFinishedBinding

class Finished : Fragment() {

    private lateinit var binding:FragmentFinishedBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_finished,container,false)


        binding.btnFinished.setOnClickListener {
            this.findNavController().navigate(FinishedDirections.actionFinishedToTitleFragment())
        }

        return binding.root
    }

}
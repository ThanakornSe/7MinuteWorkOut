package com.example.a7minuteworkout.ui

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)


        binding.flStart.setOnClickListener {
            this.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToExerciseFragment())
        }

        binding.flBMI.setOnClickListener {
            this.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToBmi())
        }

        return binding.root
    }


}
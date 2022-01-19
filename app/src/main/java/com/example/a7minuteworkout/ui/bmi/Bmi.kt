package com.example.a7minuteworkout.ui.bmi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.databinding.FragmentBmiBinding

class Bmi : Fragment() {

    private lateinit var binding:FragmentBmiBinding

    private val viewModel: BmiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_bmi,container,false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnCalculateUnits.setOnClickListener {
            if (validateMetricUnits()){
                val height = binding.etMetricUnitHeight.text.toString().toDouble()/100
                val weight = binding.etMetricUnitWeight.text.toString().toDouble()
                viewModel.calculateBMI(height,weight)
            }else {
                Toast.makeText(requireContext(), "Please enter valid values.", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.showBMI.observe(viewLifecycleOwner, Observer {
            if (it==true){
                binding.llDiplayBMIResult.visibility = View.VISIBLE
            }
        })

        return binding.root
    }


    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (binding.etMetricUnitWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (binding.etMetricUnitHeight.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

}
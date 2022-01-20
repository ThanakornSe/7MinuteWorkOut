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

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW" // Metric Unit View
        private const val US_UNITS_VIEW = "US_UNIT_VIEW" // US Unit View
    }

    private var currentUnit: String = METRIC_UNITS_VIEW

    private lateinit var binding: FragmentBmiBinding

    private val viewModel: BmiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bmi, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnCalculateUnits.setOnClickListener {
            calculateUnit()
        }

        binding.rgUnit.setOnCheckedChangeListener { _, checkId: Int ->
            if (checkId == R.id.rb_metricUnit) {
                binding.llMetricUnit.visibility = View.VISIBLE
                binding.llUsUnit.visibility = View.INVISIBLE
                currentUnit = METRIC_UNITS_VIEW
            } else {
                binding.llMetricUnit.visibility = View.INVISIBLE
                binding.llUsUnit.visibility = View.VISIBLE
                currentUnit = US_UNITS_VIEW
            }
        }

        viewModel.showBMI.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.llDisplayBMIResult.visibility = View.VISIBLE
            }
        })

        return binding.root
    }

    private fun calculateUnit() {
        if (currentUnit == METRIC_UNITS_VIEW) {
            if (validateMetricUnits()) {
                val height = binding.etMetricUnitHeight.text.toString().toDouble() / 100
                val weight = binding.etMetricUnitWeight.text.toString().toDouble()
                viewModel.calculateMetricBMI(height, weight)
            } else {
                Toast.makeText(requireContext(), "Please enter valid values.", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            if (validateUSUnits()) {
                val heightFeet = binding.etUsHeightFeet.text.toString().toDouble()
                val heightInch = binding.etUsHeightInch.text.toString().toDouble()
                val weight = binding.etUsUnitWeight.text.toString().toDouble()
                viewModel.calculateUsBMI(heightFeet, heightInch, weight)
            } else {
                Toast.makeText(requireContext(), "Please enter valid values.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
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

    private fun validateUSUnits(): Boolean {
        var isValid = true

        when {
            binding.etUsUnitWeight.text.toString().isEmpty() -> {
                isValid = false
            }
            binding.etUsHeightFeet.text.toString().isEmpty() -> {
                isValid = false
            }
            binding.etUsHeightInch.text.toString().isEmpty() -> {
                isValid = false
            }
        }
        return isValid
    }

}
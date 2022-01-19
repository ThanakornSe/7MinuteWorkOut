package com.example.a7minuteworkout.ui.bmi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.math.RoundingMode
import kotlin.math.pow

class BmiViewModel : ViewModel() {

    private var _bmi = MutableLiveData<Double>()
    private val bmi: LiveData<Double> get() = _bmi
    val bmiToString = Transformations.map(bmi) {
        it.toBigDecimal().setScale(2,RoundingMode.HALF_EVEN).toString()
    }

    private var _bmiLabel = MutableLiveData<String>()
    val bmiLabel: LiveData<String> get() = _bmiLabel

    private var _bmiDescription = MutableLiveData<String>()
    val bmiDescription: LiveData<String> get() = _bmiDescription

    private var _showBMI = MutableLiveData<Boolean>()
    val showBMI: LiveData<Boolean> get() = _showBMI


    fun calculateBMI(height: Double, weight: Double) {
        _bmi.value = weight / height.pow(2)

        disPlayBMIResult(weight / height.pow(2))
        _showBMI.value = true
    }


    private fun disPlayBMIResult(bmi: Double) {

        when {
            bmi <= 15 -> {
                _bmiLabel.value = "Very severely underweight"
                _bmiDescription.value =
                    "Oops! You really need to take better care of yourself! Eat more!"
            }
            bmi > 15 && bmi <= 16 -> {
                _bmiLabel.value = "Severely underweight"
                _bmiDescription.value =
                    "Oops!You really need to take better care of yourself! Eat more!"
            }
            bmi > 16 && bmi <= 18.5 -> {
                _bmiLabel.value = "Underweight"
                _bmiDescription.value =
                    "Oops! You really need to take better care of yourself! Eat more!"
            }
            bmi > 18.5 && bmi <= 25 -> {
                _bmiLabel.value = "Normal"
                _bmiDescription.value = "Congratulations! You are in a good shape!"
            }
            bmi > 25 && bmi <= 30 -> {
                _bmiLabel.value = "Overweight"
                _bmiDescription.value =
                    "Oops! You really need to take care of your yourself! Workout maybe!"
            }
            bmi > 30 && bmi <= 35 -> {
                _bmiLabel.value = "Obese Class | (Moderately obese)"
                _bmiDescription.value =
                    "Oops! You really need to take care of your yourself! Workout maybe!"
            }
            bmi > 35 && bmi <= 40 -> {
                _bmiLabel.value = "Obese Class || (Severely obese)"
                _bmiDescription.value = "OMG! You are in a very dangerous condition! Act now!"
            }
            else -> {
                _bmiLabel.value = "Obese Class ||| (Very Severely obese)"
                _bmiDescription.value = "OMG! You are in a very dangerous condition! Act now!"
            }
        }

    }

}
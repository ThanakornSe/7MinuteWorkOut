package com.example.a7minuteworkout

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.a7minuteworkout.databinding.ActivityMainBinding
import com.example.a7minuteworkout.databinding.DialogCustomBackConfirmationBinding
import com.example.a7minuteworkout.ui.exercise.ExerciseFragmentDirections

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = this.findNavController(R.id.nav_host_fragment_activity_main)
        NavigationUI.setupActionBarWithNavController(this, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return when(navController.currentDestination?.id) {
            R.id.exerciseFragment -> {
                val customDialog = Dialog(this)
                val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
                customDialog.setContentView(dialogBinding.root)
                customDialog.setCanceledOnTouchOutside(false)
                dialogBinding.btnYes.setOnClickListener {navController.navigate(
                    ExerciseFragmentDirections.actionExerciseFragmentToTitleFragment())
                    customDialog.dismiss()
                }
                dialogBinding.btnNo.setOnClickListener { customDialog.dismiss() }
                customDialog.show()
                true
            }
            else -> navController.navigateUp()
        }
    }
}
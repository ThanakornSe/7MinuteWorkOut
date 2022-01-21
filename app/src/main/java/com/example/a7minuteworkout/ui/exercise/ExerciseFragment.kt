package com.example.a7minuteworkout.ui.exercise

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.WorkOutApplication
import com.example.a7minuteworkout.adapter.ExerciseRvAdapter
import com.example.a7minuteworkout.databinding.DialogCustomBackConfirmationBinding
import com.example.a7minuteworkout.databinding.FragmentExerciseBinding

class ExerciseFragment : Fragment() {

    private lateinit var binding: FragmentExerciseBinding
    //private lateinit var viewModel: ExerciseViewModel
    private lateinit var adapter: ExerciseRvAdapter

    private val viewModel:ExerciseViewModel by viewModels {
        ExerciseViewModelFactory((requireNotNull(activity).application))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercise, container, false)

//        val application = requireNotNull(activity).application
//        val factory = ExerciseViewModelFactory(application)
//        viewModel = ViewModelProvider(this, factory)[ExerciseViewModel::class.java]

        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        adapter = ExerciseRvAdapter()

        viewModel.exerciseList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.rvExerciseStatus.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvExerciseStatus.adapter = adapter


        viewModel.showEx.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.flExerciseBar.visibility = View.VISIBLE
                binding.rvExerciseStatus.visibility = View.VISIBLE
                adapter.notifyDataSetChanged()
            } else {
                binding.flExerciseBar.visibility = View.GONE
                binding.rvExerciseStatus.visibility = View.GONE
            }
        })

        viewModel.showRest.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.flRestBar.visibility = View.VISIBLE
                binding.txtNextEx.visibility = View.VISIBLE
                adapter.notifyDataSetChanged()
            } else {
                binding.flRestBar.visibility = View.GONE
                binding.txtNextEx.visibility = View.GONE

            }
        })

        viewModel.showImage.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.ivWorkout.visibility = View.VISIBLE
            } else binding.ivWorkout.visibility = View.GONE
        })

        viewModel.imageSrc.observe(viewLifecycleOwner, Observer {
            binding.ivWorkout.setImageResource(it)
        })

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            customDialogForBackButton()
        }
        callback.isEnabled

        viewModel.navigateToFinished.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(ExerciseFragmentDirections.actionExerciseFragmentToFinished())
                viewModel.doneNavigateToFinished()

            }

        })



        return binding.root
    }

    private fun customDialogForBackButton(){
        val customDialog = Dialog(requireContext())
        val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener {this.findNavController().navigate(ExerciseFragmentDirections.actionExerciseFragmentToTitleFragment())
        customDialog.dismiss() }
        dialogBinding.btnNo.setOnClickListener { customDialog.dismiss() }
        customDialog.show()
    }
}


//        val actionBar = (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbarExercise)
//        val supActionBar = (activity as AppCompatActivity?)!!.supportActionBar
//        supActionBar?.setDisplayHomeAsUpEnabled(true)
//        binding.toolbarExercise.setNavigationOnClickListener { customDialogForBackButton() }




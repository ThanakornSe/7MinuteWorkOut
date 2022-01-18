package com.example.a7minuteworkout.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.adapter.ExerciseRvAdapter
import com.example.a7minuteworkout.databinding.FragmentExerciseBinding

class ExerciseFragment : Fragment() {

    private lateinit var binding:FragmentExerciseBinding
    private lateinit var viewModel: ExerciseViewModel
    private lateinit var adapter:ExerciseRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_exercise,container,false)

        val application = requireNotNull(activity).application
        val factory = ExerciseViewModelFactory(application)
        viewModel = ViewModelProvider(this,factory)[ExerciseViewModel::class.java]
        
        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        adapter = ExerciseRvAdapter(viewModel.exerciseList!!)
        binding.rvExerciseStatus.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvExerciseStatus.adapter = adapter


        viewModel.showEx.observe(viewLifecycleOwner, Observer {
            if (it == true){
                binding.flExerciseBar.visibility = View.VISIBLE
            }else binding.flExerciseBar.visibility = View.GONE
        })

        viewModel.showRest.observe(viewLifecycleOwner, Observer {
            if (it == true){
                binding.flRestBar.visibility = View.VISIBLE
                binding.txtNextEx.visibility = View.VISIBLE
            }else {
                binding.flRestBar.visibility = View.GONE
                binding.txtNextEx.visibility = View.GONE
            }
        })

        viewModel.showImage.observe(viewLifecycleOwner, Observer {
            if (it==true){
                binding.ivWorkout.visibility = View.VISIBLE
            }else binding.ivWorkout.visibility = View.GONE
        })

        viewModel.imageSrc.observe(viewLifecycleOwner, Observer {
            binding.ivWorkout.setImageResource(it)
        })


        return binding.root
    }

}



//        val actionBar = (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbarExercise)
//        val supActionBar = (activity as AppCompatActivity?)!!.supportActionBar
//        supActionBar?.setDisplayHomeAsUpEnabled(true)
//        binding.toolbarExercise.setNavigationOnClickListener { }
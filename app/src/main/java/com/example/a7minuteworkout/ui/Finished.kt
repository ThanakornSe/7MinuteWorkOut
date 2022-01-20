package com.example.a7minuteworkout.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.WorkOutApplication
import com.example.a7minuteworkout.databinding.FragmentFinishedBinding
import com.example.a7minuteworkout.model.HistoryModel
import com.example.a7minuteworkout.ui.history.HistoryViewModel
import com.example.a7minuteworkout.ui.history.HistoryViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class Finished : Fragment() {

    private lateinit var binding:FragmentFinishedBinding

    private val viewModel: HistoryViewModel by viewModels {
        val application = requireNotNull(this.activity).application
        HistoryViewModelFactory((application as WorkOutApplication).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_finished,container,false)


        binding.btnFinished.setOnClickListener {
            this.findNavController().navigate(FinishedDirections.actionFinishedToTitleFragment())
            addToHistory()
        }

        return binding.root
    }

    private fun addToHistory() {
        val dateTime = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.getDefault())
        val date = sdf.format(dateTime)
        viewModel.insert(HistoryModel(date = date))
        Toast.makeText(requireContext(),"Add to History Successfully",Toast.LENGTH_SHORT).show()
    }

}
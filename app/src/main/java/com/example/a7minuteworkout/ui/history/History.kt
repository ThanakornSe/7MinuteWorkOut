package com.example.a7minuteworkout.ui.history

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.WorkOutApplication
import com.example.a7minuteworkout.adapter.HistoryAdapter
import com.example.a7minuteworkout.databinding.HistoryFragmentBinding

class History : Fragment() {
    private lateinit var binding:HistoryFragmentBinding

    private val viewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory((activity?.application as WorkOutApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.history_fragment,container,false)

        val itemAdapter = HistoryAdapter()

        viewModel.getAllHistory.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                itemAdapter.submitList(it)
                binding.rvHistory.visibility = View.VISIBLE
                binding.btnClear.visibility = View.VISIBLE
                binding.tvNoDataAvailable.visibility = View.GONE
            }else{
                binding.rvHistory.visibility = View.GONE
                binding.btnClear.visibility = View.GONE
                binding.tvNoDataAvailable.visibility = View.VISIBLE
            }
        })

        binding.btnClear.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete All Record")
                .setMessage("Are you sure you wants to delete all record?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, _ ->
                    viewModel.clearData()
                    dialog.dismiss()
                }
                .setNegativeButton("No"){dialog,_->
                    dialog.dismiss()
                }
                .show()
        }

        binding.rvHistory.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }


        return binding.root
    }

}
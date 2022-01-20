package com.example.a7minuteworkout.ui.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.databinding.HistoryFragmentBinding

class History : Fragment() {
    private lateinit var binding:HistoryFragmentBinding

    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.history_fragment,container,false)
        return binding.root
    }

}
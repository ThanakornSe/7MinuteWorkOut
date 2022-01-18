package com.example.a7minuteworkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.ItemExerciseStatusBinding
import com.example.a7minuteworkout.model.Exercise

class ExerciseRvAdapter(private val exerciseList: List<Exercise>) :
    RecyclerView.Adapter<ExerciseRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = exerciseList[position]
        holder.binding(item)
    }

    override fun getItemCount(): Int = exerciseList.size

    inner class ViewHolder(private val binding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(exercise: Exercise) {
            binding.txtItem.text = exercise.id.toString()
        }

    }


}
package com.example.a7minuteworkout.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.databinding.ItemExerciseStatusBinding
import com.example.a7minuteworkout.model.Exercise

class ExerciseRvAdapter : ListAdapter<Exercise, ExerciseRvAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item)
    }

    class ViewHolder(private val binding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(exercise: Exercise) {
            binding.txtItem.text = exercise.id.toString()

            when {
                exercise.isSelected -> {
                    binding.txtItem.background = ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.item_circular_thin_color_accent_border
                    )
                    binding.txtItem.setTextColor(Color.parseColor("#212121"))
                }
                exercise.isComplete -> {
                    binding.txtItem.background = ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.item_circular_color_accent_background
                    )
                    binding.txtItem.setTextColor(Color.parseColor("#FFFFFF"))
                }
                else -> {
                    binding.txtItem.background = ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.item_circular_color_gray_background)
                    binding.txtItem.setTextColor(Color.parseColor("#212121"))
                }
            }
        }


    }

    companion object DiffCallback : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }
    }


}
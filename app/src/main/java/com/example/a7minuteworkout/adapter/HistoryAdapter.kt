package com.example.a7minuteworkout.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.ItemHistoryRowBinding
import com.example.a7minuteworkout.model.HistoryModel

class HistoryAdapter:ListAdapter<HistoryModel,HistoryAdapter.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item)

        holder.binding.tvPosition.text = (position+1).toString()

        if (position % 2 == 0) {
            holder.binding.llHistoryItemMain.setBackgroundColor(
                Color.parseColor("#EBEBEB")
            )
        } else {
            holder.binding.llHistoryItemMain.setBackgroundColor(
                Color.parseColor("#FFFFFF")
            )
        }

    }


    class ViewHolder(val binding:ItemHistoryRowBinding):RecyclerView.ViewHolder(binding.root){
        fun binding(historyModel: HistoryModel){
            binding.tvItem.text = historyModel.date
        }
    }

    companion object DiffCallBack:DiffUtil.ItemCallback<HistoryModel>(){
        override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
            return oldItem == newItem
        }

    }


}
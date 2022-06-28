package com.example.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.databinding.ItemClimateBinding
import com.example.myapplication.domain.model.Daily
import com.example.myapplication.presentation.viewholder.ClimateViewHolder
import com.example.myapplication.presentation.base.BaseAdapter
import com.santander.globile.superherotest.ui.base.BaseViewHolder

class ClimateAdapter : BaseAdapter<Daily>() {

    override var onItemClicked: (data: Daily) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Daily> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemClimateBinding.inflate(layoutInflater, parent, false)
        return ClimateViewHolder(binding) {
            onItemClicked(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Daily>, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun set(data: List<Daily>) {
        submitList(data)
    }


}
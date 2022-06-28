package com.example.myapplication.presentation.viewholder

import com.example.myapplication.databinding.ItemClimateBinding
import com.example.myapplication.domain.model.Daily
import com.santander.globile.superherotest.ui.base.BaseViewHolder

class ClimateViewHolder(
    private val binding: ItemClimateBinding,
    override val onClick: (data: Int) -> Unit
) : BaseViewHolder<Daily>(binding) {

    companion object{
        var dia = 1
    }

    override fun bindTo(data: Daily) {
        binding.data = data
        binding.dia = dia++
        binding.executePendingBindings()
    }


}
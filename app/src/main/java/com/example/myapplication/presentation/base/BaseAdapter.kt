package com.example.myapplication.presentation.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.santander.globile.superherotest.ui.base.BaseViewHolder

abstract class BaseAdapter<T> : ListAdapter<T, BaseViewHolder<T>>(BaseItemCallback<T>()) {

    abstract val onItemClicked: (data: T) -> Unit

    abstract fun set(data: List<T>)

    class BaseItemCallback<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.toString() == newItem.toString()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

    }
}
package com.example.moviees.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.moviees.BR

abstract class BaseAdapter<T> (private var oldResponseList: List<T?> , private val listener: BaseListener) :RecyclerView.Adapter <BaseAdapter.BaseViewHolder>() {
    abstract val layoutId: Int
    abstract fun setNewData(newResponseList: List<T?>)
    override fun getItemCount(): Int = oldResponseList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context) , layoutId , parent , false ) )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentMovie = oldResponseList[position]
        when( holder ) {
            is ItemViewHolder -> {
                holder.binding.apply {
                    setVariable(BR.movie , currentMovie)
                    setVariable(BR.listener , listener)
                }
            }
        }
    }

    abstract class BaseViewHolder( binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
    class ItemViewHolder (val binding: ViewDataBinding) :BaseViewHolder(binding)
}
package com.example.moviees.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moviees.model.topRated.Result

class MyDiffUtil(private val newResponse: List<Result?>, private val oldResponse: List<Result?>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldResponse.size

    override fun getNewListSize(): Int = newResponse.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldResponse[oldItemPosition] == newResponse[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldResponse[oldItemPosition]?.id == newResponse[newItemPosition]?.id
    }

}

package com.example.moviees.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviees.BR
import com.example.moviees.R
import com.example.moviees.databinding.ItemForMovieBinding
import com.example.moviees.model.topRated.Result
import javax.inject.Inject


class MovieItemAdapter constructor(private var oldResponseList: List<Result?>, val movieListener: BaseListener) :BaseAdapter<Result>(oldResponseList, movieListener) {

    override val layoutId: Int = R.layout.item_for_movie

    override fun setNewData(newResponseList: List<Result?>) {
        val diffUtil = MyDiffUtil(newResponseList , oldResponseList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldResponseList = newResponseList
        diffResults.dispatchUpdatesTo(this)
    }

}


//    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        var currentMovie = oldResponseList[position]
//        holder.binding.apply {
//            movie = currentMovie
//            listener = movieListener
//            executePendingBindings()
//        }
//    }



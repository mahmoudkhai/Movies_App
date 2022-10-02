package com.example.moviees.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.moviees.R
import com.example.moviees.databinding.MovieDetailsFragmentBinding
import com.example.moviees.viewModels.TopRatedMoviesViewModel
import com.example.moviees.viewModels.TopRatedViewModelFactory


class MovieDetailsFragment : Fragment() {
    lateinit var binding: MovieDetailsFragmentBinding
    val args: MovieDetailsFragmentArgs by  navArgs()
    lateinit var moviesViewModel: TopRatedViewModelFactory
    lateinit var viewModel: TopRatedMoviesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailsFragmentBinding.inflate(layoutInflater)
        args.movieId
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
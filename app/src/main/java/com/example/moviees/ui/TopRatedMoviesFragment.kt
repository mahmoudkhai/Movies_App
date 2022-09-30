package com.example.moviees.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.moviees.R
import com.example.moviees.adapter.MovieInterActionListener
import com.example.moviees.adapter.MovieItemAdapter
import com.example.moviees.api.MovieApi
import com.example.moviees.database.MoviesDatabase
import com.example.moviees.databinding.FragmentTopRatedMoviesBinding
import com.example.moviees.hilt_di.Module
import com.example.moviees.model.topRated.Result
import com.example.moviees.repository.LatestMovieRepository
import com.example.moviees.repository.MoviesRepository
import com.example.moviees.util.NetworkResponse
import com.example.moviees.viewModels.TopRatedMoviesViewModel
import com.example.moviees.viewModels.TopRatedViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class TopRatedMoviesFragment : Fragment()//    R.layout.fragment_top_rated_movies
    , MovieInterActionListener {
    //    private val moviesViewModel: TopRatedMoviesViewModel by viewModels()
    private lateinit var binding: FragmentTopRatedMoviesBinding
    private lateinit var api:MovieApi
    private lateinit var database: MoviesDatabase
    private lateinit var repository: LatestMovieRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopRatedMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         api = Module.getApiInstance()
         database = MoviesDatabase.getDatabase(requireActivity())
         repository = MoviesRepository(database, api)
        val moviesViewModel = TopRatedViewModelFactory(repository = repository)
        val viewModel: TopRatedMoviesViewModel =
            ViewModelProvider(
                requireActivity(),
                moviesViewModel
            ).get(TopRatedMoviesViewModel::class.java)
    }

    //        moviesViewModel.result.observe(viewLifecycleOwner) { networkResponse ->
//            if(networkResponse is NetworkResponse.Success) {
//                adapter.setData(networkResponse.data)
//            }
//
//        }
    override fun onMovieClicked(movie: Result) {
        Navigation.findNavController(binding.moviesRecyclarView)
            .navigate(R.id.action_topRatedMoviesFragment_to_movieDetailsFragment)
    }
    //if we want to collect data here
//        moviesViewModel.liveData.observe(viewLifecycleOwner ){
////            repeatOnLifecycle(lifecycle.currentState  ) use it to stop recieving data if you're in background
//            when (it) {
//                is NetworkResponse.Error<*> -> TODO()
//                NetworkResponse.Loading -> TODO()
//                is NetworkResponse.Success -> adapter.setData(it.data?.results)
//            }
//        }
//    }
}
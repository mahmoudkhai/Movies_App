package com.example.moviees.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

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

//@AndroidEntryPoint
class TopRatedMoviesFragment : Fragment()//    R.layout.fragment_top_rated_movies
    , MovieInterActionListener {
    //    private val moviesViewModel: TopRatedMoviesViewModel by viewModels()
    private lateinit var binding: FragmentTopRatedMoviesBinding
    private lateinit var api: MovieApi
    private lateinit var database: MoviesDatabase
    private lateinit var repository: LatestMovieRepository
    lateinit var moviesViewModel: TopRatedViewModelFactory
    lateinit var viewModel: TopRatedMoviesViewModel
    lateinit var adapter: MovieItemAdapter

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
        moviesViewModel = TopRatedViewModelFactory(repository = repository)
        viewModel = ViewModelProvider(
            requireActivity(),
            moviesViewModel
        ).get(TopRatedMoviesViewModel::class.java)
        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResponse.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE
                    binding.error.text = it.errorBody.toString()
                }
                is NetworkResponse.Loading -> {
                    binding.error.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResponse.Success -> {
                    binding.error.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    adapter = MovieItemAdapter(it.data!!.results , this)
                    binding.moviesRecyclarView.adapter = adapter

                }
            }
        }

    }

    //        moviesViewModel.result.observe(viewLifecycleOwner) { networkResponse ->
//            if(networkResponse is NetworkResponse.Success) {
//                adapter.setData(networkResponse.data)
//            }
//
//        }
    override fun onMovieClicked(movie: Result) {
        val action =
            TopRatedMoviesFragmentDirections.actionTopRatedMoviesFragmentToMovieDetailsFragment(
                movie.id
            )
        view?.findNavController()?.navigate(action)
//        Navigation.findNavController(binding.moviesRecyclarView)
//            .navigate(R.id.action_topRatedMoviesFragment_to_movieDetailsFragment)
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
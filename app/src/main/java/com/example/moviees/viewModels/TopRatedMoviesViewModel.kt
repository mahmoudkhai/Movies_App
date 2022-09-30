package com.example.moviees.viewModels

import androidx.lifecycle.*
import com.example.moviees.model.topRated.Result
import com.example.moviees.repository.LatestMovieRepository
import com.example.moviees.util.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


//@HiltViewModel
class TopRatedMoviesViewModel @Inject constructor(
     private val repo: LatestMovieRepository,
) : ViewModel()  {

    lateinit var  result: LiveData<NetworkResponse<List<Result?>>>
    init {
    result = repo.getTopRatedMovies().asLiveData()
    }




//        fun getLatestMovie(apiKey:String) {
//
//            viewModelScope.launch() {
//                //intermediate operators can be used with flow ,each operator creates a new flow due to it's functionality , are called upStream flow
//                repo.getTopRatedMovies(apiKey).collect {
//                    _result.postValue(it)
//                    when(it) {
//                        is NetworkResponse.Loading-> liveData.postValue(it)
//                        is NetworkResponse.Success -> { liveData.postValue(it)
//                        }
//                        is NetworkResponse.Error<*> -> { liveData.postValue(it)
//                        }
//                        else -> { }
//                    }
//
//                }
//            }
//        }
}

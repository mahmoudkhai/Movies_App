package com.example.moviees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.moviees.adapter.MovieInterActionListener
import com.example.moviees.adapter.MovieItemAdapter
import com.example.moviees.database.MoviesDatabase
import com.example.moviees.databinding.ActivityMainBinding
import com.example.moviees.databinding.FragmentTopRatedMoviesBinding
import com.example.moviees.hilt_di.Module
import com.example.moviees.model.topRated.Result
import com.example.moviees.repository.LatestMovieRepository
import com.example.moviees.repository.MoviesRepository
import com.example.moviees.ui.TopRatedMoviesFragment
import com.example.moviees.viewModels.TopRatedViewModelFactory

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binidng: ActivityMainBinding
    lateinit var navController:NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binidng = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binidng.lifecycleOwner = this // allows u to observe changes of live data
//        navController = findNavController(R.id.fragmentContainerView) // not possible for the view of NavHostFragment to be available in the onCreate
//        supportFragmentManager.beginTransaction().add(R.id.topRatedMoviesFragment , TopRatedMoviesFragment() , null).commit()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
        binidng.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        navController = findNavController(R.id.fragmentContainerView)
    }

    //     to set up back button in action bar
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
package com.prmto.kekodflashcard.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private val viewModel by viewModels<MainActivityViewModel>()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavController()
        setNavGraph()
        setBottomNavigationView()
    }

    private fun setBottomNavigationView() {
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun setNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setNavGraph() {

        val navGraph = if (viewModel.finishOnboarding()) {
            R.navigation.onboarding_nav_graph
        } else {
            R.navigation.main_nav_graph
        }

        navController.setGraph(navGraph)
    }
}
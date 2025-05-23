package com.example.recommendedarch.mainModule.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recommendedarch.R
import com.example.recommendedarch.common.viewModel.ShareViewModel
import com.example.recommendedarch.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/****
 * Project: Wines
 * From: com.cursosant.wines
 * Created by Alain Nicolás Tello on 06/02/24 at 20:23
 * All rights reserved 2024.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val vm: ShareViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)

        launchLoginUI()
        setupObservers()
    }

    private fun setupObservers() {
        vm.showNavView.observe(this) { showNavView ->
            setupNavView(showNavView)
        }

        vm.isSignOut.observe(this) { isSignOut ->
            if (isSignOut) {
                setupNavView(false)
                launchLoginUI()
            }
        }
    }

    private fun launchLoginUI() {
        navController.navigate(R.id.navigation_login)
    }

    private fun setupNavView(isVisible: Boolean) {
        binding.navView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
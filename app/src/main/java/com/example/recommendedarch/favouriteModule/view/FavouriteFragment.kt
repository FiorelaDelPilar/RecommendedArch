package com.example.recommendedarch.favouriteModule.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recommendedarch.BR
import com.example.recommendedarch.updateModule.view.UpdateDialogFragment
import com.example.recommendedarch.common.utils.Constants
import com.example.recommendedarch.common.utils.OnClickListener
import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.common.view.WineBaseFragment
import com.example.recommendedarch.favouriteModule.model.FavouriteRepository
import com.example.recommendedarch.favouriteModule.model.domain.FavouriteRoomDatabase
import com.example.recommendedarch.favouriteModule.viewModel.FavouriteViewModel
import com.example.recommendedarch.favouriteModule.viewModel.FavouriteViewModelFactory
import com.example.recommendedarch.homeModule.view.WineDiff
import com.google.android.material.snackbar.Snackbar

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
class FavouriteFragment : WineBaseFragment(), OnClickListener {

    private lateinit var adapter: WineFavListAdapter
    private lateinit var vm: FavouriteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupAdapter()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupViewModel() {
        vm = ViewModelProvider(
            this,
            FavouriteViewModelFactory(FavouriteRepository(FavouriteRoomDatabase()))
        )[FavouriteViewModel::class.java]
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupObservers() {
        binding.viewModel?.let { vm ->
            vm.snackbarMsg.observe(viewLifecycleOwner) { resMsg ->
                resMsg?.let { showMsg(resMsg) }
            }
            vm.wines.observe(viewLifecycleOwner) { wines ->
                adapter.submitList(wines)
            }
        }
    }


    private fun setupAdapter() {
        adapter = WineFavListAdapter(this, WineDiff())
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@FavouriteFragment.adapter
        }
    }

    private fun showMsg(msgRes: Int) {
        Snackbar.make(binding.root, msgRes, Snackbar.LENGTH_SHORT).show()
    }

    /*
    * OnClickListener
    * */
    override fun onFavorite(wine: Wine) {
        wine.isFavorite = !wine.isFavorite
        if (wine.isFavorite) vm.addWine(wine) else vm.deleteWine(wine)
    }

    override fun onLongClick(wine: Wine) {
        val fragmentManager = childFragmentManager
        val fragment = UpdateDialogFragment()
        val args = Bundle()
        args.putDouble(Constants.ARG_ID, wine.id)
        fragment.arguments = args
        fragment.show(fragmentManager, UpdateDialogFragment::class.java.simpleName)
        fragment.setOnUpdateListener {
            binding.srlResults.isRefreshing = true
            binding.viewModel?.getAllWines()
        }
    }
}
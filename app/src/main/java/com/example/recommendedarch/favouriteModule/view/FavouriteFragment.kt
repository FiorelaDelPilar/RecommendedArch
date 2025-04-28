package com.example.recommendedarch.favouriteModule.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recommendedarch.BR
import com.example.recommendedarch.updateModule.view.UpdateDialogFragment
import com.example.recommendedarch.common.utils.Constants
import com.example.recommendedarch.common.utils.OnClickListener
import com.example.recommendedarch.common.entities.Wine
import com.example.recommendedarch.common.view.WineBaseFragment
import com.example.recommendedarch.favouriteModule.viewModel.FavouriteViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

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

    private val adapter: WineFavListAdapter by inject { parametersOf(this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupViewModel() {
        val vm: FavouriteViewModel by inject()
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
        (binding.viewModel as? FavouriteViewModel)?.updateFavourite(wine)
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
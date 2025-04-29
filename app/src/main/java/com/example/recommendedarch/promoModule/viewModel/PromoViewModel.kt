package com.example.recommendedarch.promoModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recommendedarch.common.entities.Promo
import com.example.recommendedarch.common.viewModel.BaseViewModel
import com.example.recommendedarch.promoModule.model.PromoRepository

class PromoViewModel(private val repository: PromoRepository) : BaseViewModel() {
    private val _promos = MutableLiveData<List<Promo>>()
    val promos: LiveData<List<Promo>> = _promos

    init {
        getPromos()
    }

    private fun getPromos() {
        executeAction {
            repository.getPromos { result ->
                _promos.value = result
            }
        }
    }

    override fun onPause() = clearValues()
}
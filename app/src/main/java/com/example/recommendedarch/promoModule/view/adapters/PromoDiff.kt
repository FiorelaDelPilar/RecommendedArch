package com.example.recommendedarch.promoModule.view.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.recommendedarch.common.entities.Promo

class PromoDiff : DiffUtil.ItemCallback<Promo>() {
    override fun areItemsTheSame(oldItem: Promo, newItem: Promo) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Promo, newItem: Promo) = oldItem == newItem
}
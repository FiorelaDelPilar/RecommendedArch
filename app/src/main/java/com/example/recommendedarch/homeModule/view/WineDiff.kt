package com.example.recommendedarch.homeModule.view

import androidx.recyclerview.widget.DiffUtil
import com.example.recommendedarch.common.entities.Wine

class WineDiff : DiffUtil.ItemCallback<Wine>() {
    override fun areItemsTheSame(oldItem: Wine, newItem: Wine) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Wine, newItem: Wine) = oldItem == newItem
}
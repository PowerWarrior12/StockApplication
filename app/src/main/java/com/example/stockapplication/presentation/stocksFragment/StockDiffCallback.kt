package com.example.stockapplication.presentation.stocksFragment

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.stockapplication.domain.entities.CompanyStock

object StockDiffCallback: DiffUtil.ItemCallback<CompanyStock>() {
    override fun areItemsTheSame(oldItem: CompanyStock, newItem: CompanyStock): Boolean {
        Log.d("DiffCallback", "areItemsTheSame")
        return oldItem.symbol == newItem.symbol
    }

    override fun areContentsTheSame(oldItem: CompanyStock, newItem: CompanyStock): Boolean {
        Log.d("DiffCallback", "areContentsTheSame")
        return oldItem == newItem
    }
}
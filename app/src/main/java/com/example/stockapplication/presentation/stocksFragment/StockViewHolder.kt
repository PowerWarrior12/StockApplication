package com.example.stockapplication.presentation.stocksFragment

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.stockapplication.R
import com.example.stockapplication.databinding.ItemStockBinding
import com.example.stockapplication.domain.entities.CompanyStock
import com.example.stockapplication.presentation.StockApplication

class StockViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding: ItemStockBinding by viewBinding()

    @SuppressLint("UseCompatLoadingForDrawables")
    fun bind(companyStock: CompanyStock) {
        binding.textSymbol.text = companyStock.symbol
        binding.textTitle.text = companyStock.title
        binding.textPrice.text = if (companyStock.price != null) companyStock.price.toString() else ""
        if (companyStock.logoPath != null)
            binding.imageLogo.setImageByUrl(companyStock.logoPath)
        else
            binding.imageLogo.setImageDrawable(StockApplication.INSTANCE?.getDrawable(R.drawable.placeholder))
    }

    fun ImageView.setImageByUrl(url: String) {
        Glide
            .with(context)
            .load(url)
            .placeholder(R.drawable.placeholder)
            .into(this)
    }
}
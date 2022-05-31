package com.example.stockapplication.presentation.stocksFragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stockapplication.R
import com.example.stockapplication.domain.entities.CompanyStock

class StocksAdapter(private val itemCallback: ItemCallback) : ListAdapter<CompanyStock, StockViewHolder>(StockDiffCallback) {

    var startPosition = -1
    var endPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        return StockViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_stock, parent, false))
    }

    override fun submitList(list: MutableList<CompanyStock>?) {
        super.submitList(list)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val item = getItem(position)
        Log.d("BIND", "$position, $item")
        if (item.price == null) {
            itemCallback.onBindCallback(item)
        }
        holder.bind(item)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val startPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val endPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (startPosition != this@StocksAdapter.startPosition || endPosition != this@StocksAdapter.endPosition) {
                    this@StocksAdapter.startPosition = startPosition
                    this@StocksAdapter.endPosition = endPosition
                    itemCallback.onScrollCallback(startPosition, endPosition)
                }
            }
        })
    }

    interface ItemCallback {
        fun onScrollCallback(startPosition: Int, endPosition: Int)
        fun onBindCallback(item: CompanyStock)
    }
}
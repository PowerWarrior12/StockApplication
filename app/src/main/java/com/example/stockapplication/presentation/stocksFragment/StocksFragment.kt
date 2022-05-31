package com.example.stockapplication.presentation.stocksFragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.stockapplication.R
import com.example.stockapplication.databinding.FragmentStockListBinding
import com.example.stockapplication.domain.entities.CompanyStock
import com.example.stockapplication.presentation.StockApplication
import javax.inject.Inject

class StocksFragment : Fragment(R.layout.fragment_stock_list) {

    @Inject
    lateinit var stocksViewModelFactory: StocksViewModel.StocksViewModelFactory

    private val binding by viewBinding(FragmentStockListBinding::bind)

    private val viewModel by viewModels<StocksViewModel> { stocksViewModelFactory }

    private val stocksAdapter = StocksAdapter(object: StocksAdapter.ItemCallback {

        override fun onScrollCallback(startPosition: Int, endPosition: Int) {
            viewModel.visibleStockItemsChanged(startPosition, endPosition)
        }

        override fun onBindCallback(item: CompanyStock) {
            viewModel.stockWasBound(item)
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        StockApplication.INSTANCE?.appComponent?.inject(this)
    }

    private fun initViews() {
        binding.stockRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.stockRecycler.adapter = stocksAdapter
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.stockRecycler.addItemDecoration(decoration)
    }

    private fun observeData() {
        viewModel.loadLiveData.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.stockRecycler.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.gray_200, context?.theme))
            } else {
                binding.stockRecycler.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.white, context?.theme))
            }
        }
        viewModel.companyStocksLiveData.observe(viewLifecycleOwner) { stocksList ->
            stocksAdapter.submitList(stocksList)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
            binding.stockRecycler.isVisible = !error
            binding.errorImage.isVisible = error
        }
    }

    companion object {
        fun newInstance(): StocksFragment = StocksFragment()
    }
}
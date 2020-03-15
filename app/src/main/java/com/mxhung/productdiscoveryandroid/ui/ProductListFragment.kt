package com.mxhung.productdiscoveryandroid.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mxhung.productdiscoveryandroid.viewmodel.ProductListViewModel
import com.mxhung.productdiscoveryandroid.adapter.ProductAdapter
import com.mxhung.productdiscoveryandroid.databinding.FragmentProductListBinding
import com.mxhung.productdiscoveryandroid.di.Injectable
import com.mxhung.productdiscoveryandroid.util.autoCleared
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProductListFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val productListViewModel: ProductListViewModel by viewModels {
        viewModelFactory
    }
    val adapter = ProductAdapter()

    var binding by autoCleared<FragmentProductListBinding>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentProductListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.rlProductList.adapter = adapter
        return binding.root
    }

    private fun subscribeUi(adapter: ProductAdapter) {
        productListViewModel.results.observe(viewLifecycleOwner, Observer { result ->
            Log.d("hungmx ", "${result.data}")
            adapter.submitList(result.data)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi(adapter)
    }
}

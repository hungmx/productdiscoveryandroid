package com.mxhung.productdiscoveryandroid.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.mxhung.productdiscoveryandroid.ProductDetailFragmentArgs
import com.mxhung.productdiscoveryandroid.viewmodel.ProductDetailViewModel
import com.mxhung.productdiscoveryandroid.R
import com.mxhung.productdiscoveryandroid.binding.FragmentDataBindingComponent
import com.mxhung.productdiscoveryandroid.databinding.FragmentProductDetailBinding
import com.mxhung.productdiscoveryandroid.di.Injectable
import com.mxhung.productdiscoveryandroid.util.autoCleared
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val productDetailViewModel: ProductDetailViewModel by viewModels {
        viewModelFactory
    }
    var binding by autoCleared<FragmentProductDetailBinding>()
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private val params by navArgs<ProductDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentProductDetailBinding>(
            inflater,
            R.layout.fragment_product_detail,
            container,
            false,
            dataBindingComponent
        )
        binding = dataBinding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.args = params
        productDetailViewModel.setProductSku(params.sku.toString())

        productDetailViewModel.results.observe(viewLifecycleOwner, Observer {result ->
            Log.d("hungmx ", "ProductDetailFragment ${result.data}")
            binding.product = result.data
        })
    }

}

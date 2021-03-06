package com.mxhung.productdiscoveryandroid.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mxhung.productdiscoveryandroid.R
import com.mxhung.productdiscoveryandroid.databinding.ItemProductBinding
import com.mxhung.productdiscoveryandroid.model.Products
import com.mxhung.productdiscoveryandroid.view.ProductListFragmentDirections

class ProductAdapter : ListAdapter<Products, RecyclerView.ViewHolder> (ProductDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)
        (holder as ProductViewHolder).bind(product)
        Log.d("hungmx ", " position $position")
        positionProduct = position
    }
    class ProductViewHolder(private val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.setClickListener{
                binding.product?.let { products ->
                    navigateToProduct(products, it)
                }
            }
        }
        fun bind(item : Products){
            if (positionProduct % 2 == 0)
                binding.imgProduct.setImageResource(R.drawable.tivi_lg)
            else
                binding.imgProduct.setImageResource(R.drawable.tivi_samsung)

            binding.apply {
                product = item
                executePendingBindings()
            }
        }
       private fun navigateToProduct(products: Products, view:View){
           val direction = ProductListFragmentDirections.actionShowProductDetail(
               products.sku
           )
           view.findNavController().navigate(direction)
       }
    }

    companion object {
        var positionProduct: Int = 0
    }
}
private class ProductDiffCallback : DiffUtil.ItemCallback<Products>() {

    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.sku == newItem.sku
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }
}
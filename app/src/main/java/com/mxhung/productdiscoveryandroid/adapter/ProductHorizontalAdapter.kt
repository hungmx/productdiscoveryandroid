package com.mxhung.productdiscoveryandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mxhung.productdiscoveryandroid.databinding.ItemProductHorizontalBinding
import com.mxhung.productdiscoveryandroid.model.Products

class ProductHorizontalAdapter : ListAdapter<Products, RecyclerView.ViewHolder> (ProductHorizontalDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(ItemProductHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)
        (holder as ProductViewHolder).bind(product)
    }
    class ProductViewHolder(private val binding: ItemProductHorizontalBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item : Products){
            binding.apply {
                product = item
                executePendingBindings()
            }
        }
    }
}
private class ProductHorizontalDiffCallback : DiffUtil.ItemCallback<Products>() {

    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.sku == newItem.sku
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }
}
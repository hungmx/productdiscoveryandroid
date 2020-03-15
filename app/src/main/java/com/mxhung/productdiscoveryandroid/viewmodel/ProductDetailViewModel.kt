package com.mxhung.productdiscoveryandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.mxhung.productdiscoveryandroid.model.Products
import com.mxhung.productdiscoveryandroid.model.Resource
import com.mxhung.productdiscoveryandroid.repository.RepoRepository
import com.mxhung.productdiscoveryandroid.util.AbsentLiveData
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(val repoRepository: RepoRepository) : ViewModel() {

    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query
    private val _productSku = MutableLiveData<String?>()
    val productSku: LiveData<String?>
        get() = _productSku

    private val _results = MutableLiveData<LiveData<Resource<Products>>>()

    //    val results : MutableLiveData<LiveData<Resource<Products>>> = _results
//    val results: LiveData<Resource<Products>> = repoRepository.getProductDetail("1200010")
    val results: LiveData<Resource<Products>> = _productSku.switchMap { productSku ->
        if (productSku == null) {
            AbsentLiveData.create()
        } else {
            repoRepository.getProductDetail(productSku)
        }
    }

    //    fun getProductDetail(productSku: String){
//        _results.value = repoRepository.getProductDetail(productSku)
//    }
    fun setProductSku(productSku: String?) {
        if (_productSku.value != productSku) {
            _productSku.value = productSku
        }
    }
}

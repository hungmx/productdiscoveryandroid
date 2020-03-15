/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

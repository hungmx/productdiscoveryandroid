package com.mxhung.productdiscoveryandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mxhung.productdiscoveryandroid.model.Products
import com.mxhung.productdiscoveryandroid.model.Resource
import com.mxhung.productdiscoveryandroid.repository.RepoRepository
import javax.inject.Inject

class ProductListViewModel @Inject constructor(repoRepository: RepoRepository) : ViewModel() {

    private val _query = MutableLiveData<String>()
    val query : LiveData<String> = _query

    val results: LiveData<Resource<List<Products>>> = repoRepository.search("")


}

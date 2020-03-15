package com.mxhung.productdiscoveryandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Demo thông tin sản phẩm: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}
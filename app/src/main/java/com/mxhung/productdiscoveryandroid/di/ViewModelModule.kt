package com.mxhung.productdiscoveryandroid.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mxhung.productdiscoveryandroid.viewmodel.ProductDetailViewModel
import com.mxhung.productdiscoveryandroid.viewmodel.ProductListViewModel
import com.mxhung.productdiscoveryandroid.viewmodel.ProductDiscoveryViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel::class)
    abstract fun bindProductDetailViewModel(productDetailViewModel: ProductDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    abstract fun bindSearchViewModel(productListViewModel: ProductListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ProductDiscoveryViewModelFactory): ViewModelProvider.Factory
}

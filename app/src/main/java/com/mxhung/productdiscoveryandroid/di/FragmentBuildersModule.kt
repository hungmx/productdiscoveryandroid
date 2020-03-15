package com.mxhung.productdiscoveryandroid.di

import com.mxhung.productdiscoveryandroid.view.ProductDetailFragment
import com.mxhung.productdiscoveryandroid.view.ProductListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun productListFragment(): ProductListFragment
    @ContributesAndroidInjector
    abstract fun productDetailViewModel(): ProductDetailFragment

}

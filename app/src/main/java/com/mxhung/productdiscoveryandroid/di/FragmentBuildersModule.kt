package com.mxhung.productdiscoveryandroid.di

import com.mxhung.productdiscoveryandroid.ui.ProductDetailFragment
import com.mxhung.productdiscoveryandroid.ui.ProductListFragment
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

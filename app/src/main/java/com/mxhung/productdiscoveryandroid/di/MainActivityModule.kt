package com.mxhung.productdiscoveryandroid.di

import com.mxhung.productdiscoveryandroid.MainActivity
import com.mxhung.productdiscoveryandroid.di.FragmentBuildersModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}

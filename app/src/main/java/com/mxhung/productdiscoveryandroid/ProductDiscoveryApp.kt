package com.mxhung.productdiscoveryandroid

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.mxhung.productdiscoveryandroid.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class ProductDiscoveryApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        Stetho.initializeWithDefaults(this);

    }

    override fun activityInjector() = dispatchingAndroidInjector
}

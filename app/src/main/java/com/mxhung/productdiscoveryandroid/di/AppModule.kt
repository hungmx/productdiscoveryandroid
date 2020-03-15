package com.mxhung.productdiscoveryandroid.di

import android.app.Application
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.mxhung.productdiscoveryandroid.api.ProductDiscoveryService
import com.mxhung.productdiscoveryandroid.db.ProductDiscoveryDB
import com.mxhung.productdiscoveryandroid.db.ProductDao
import com.mxhung.productdiscoveryandroid.util.Constant.Companion.BASE_URL
import com.mxhung.productdiscoveryandroid.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideProductService(): ProductDiscoveryService {
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ProductDiscoveryService::class.java)
    }

   @Singleton
    @Provides
    fun provideDb(app: Application): ProductDiscoveryDB {
        return Room
            .databaseBuilder(app, ProductDiscoveryDB::class.java, "productDiscovery.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRepoDao(db: ProductDiscoveryDB): ProductDao {
        return db.repoDao()
    }
}

package com.mxhung.productdiscoveryandroid.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.mxhung.productdiscoveryandroid.api.ProductDiscoveryResponse
import com.mxhung.productdiscoveryandroid.model.Extra
import com.mxhung.productdiscoveryandroid.model.Products

/**
 * Main database description.
 */
@Database(
    entities = [
        ProductDiscoveryResponse::class,
        Products::class,
        Extra::class
        ],
    version = 1,
    exportSchema = false
)
abstract class ProductDiscoveryDB : RoomDatabase() {
    abstract fun repoDao(): ProductDao
}

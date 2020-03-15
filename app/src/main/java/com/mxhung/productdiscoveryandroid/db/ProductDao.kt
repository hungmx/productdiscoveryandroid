package com.mxhung.productdiscoveryandroid.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mxhung.productdiscoveryandroid.model.Products

/**
 * Interface for database access on Product related operations.
 */
@Dao
abstract class ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRepos(repositories: List<Products>)

    @Query("SELECT * FROM Products")
    abstract fun search(): LiveData<List<Products>>

    @Query("SELECT * FROM Products WHERE `sku` = :productSku")
    abstract fun getProduct(productSku: String): LiveData<Products>

}

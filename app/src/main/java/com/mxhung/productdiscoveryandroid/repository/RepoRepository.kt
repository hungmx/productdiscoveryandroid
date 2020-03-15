package com.mxhung.productdiscoveryandroid.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.mxhung.productdiscoveryandroid.api.ApiSuccessResponse
import com.mxhung.productdiscoveryandroid.api.ProductDiscoveryService
import com.mxhung.productdiscoveryandroid.api.ProductDiscoveryResponse
import com.mxhung.productdiscoveryandroid.db.ProductDiscoveryDB
import com.mxhung.productdiscoveryandroid.db.ProductDao
import com.mxhung.productdiscoveryandroid.model.Products
import com.mxhung.productdiscoveryandroid.model.Resource
import com.mxhung.productdiscoveryandroid.util.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles Repo instances.
 *
 * unfortunate naming :/ .
 * Repo - value object name
 * Repository - type of this class.
 */
@Singleton
class RepoRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val db: ProductDiscoveryDB,
    private val productDao: ProductDao,
    private val productService: ProductDiscoveryService
) {

    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

   fun getProductDetail(productSku: String): LiveData<Resource<Products>> {
       return object : NetworkBoundResource<Products, ProductDiscoveryResponse>(appExecutors) {
           override fun saveCallResult(item: ProductDiscoveryResponse) {
           }

           override fun shouldFetch(data: Products?) = data == null

           override fun loadFromDb() = productDao.getProduct(productSku)

           override fun createCall() = productService.getProductDetail(productSku)
       }.asLiveData()
   }
    fun search(query: String): LiveData<Resource<List<Products>>> {
        return object : NetworkBoundResource<List<Products>, ProductDiscoveryResponse>(appExecutors) {

            override fun saveCallResult(item: ProductDiscoveryResponse) {

                Log.d("hungmx search ", item.result.products.toString())
                db.runInTransaction {
                    productDao.insertRepos(item.result.products)
                }
            }

            override fun shouldFetch(data: List<Products>?): Boolean{
                return data == null || data.isEmpty() || repoListRateLimit.shouldFetch(query)
            }

            override fun loadFromDb(): LiveData<List<Products>> {
                return productDao.search()
            }

            override fun createCall() = productService.searchRepos(query)

            override fun processResponse(response: ApiSuccessResponse<ProductDiscoveryResponse>)
                    : ProductDiscoveryResponse {
                return response.body
            }

            override fun onFetchFailed() {
                super.onFetchFailed()
                repoListRateLimit.reset(query)
            }
        }.asLiveData()
    }
}

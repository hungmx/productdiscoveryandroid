package com.mxhung.productdiscoveryandroid.api

import androidx.lifecycle.LiveData
import com.mxhung.productdiscoveryandroid.util.Constant.Companion.DETAIL_API
import com.mxhung.productdiscoveryandroid.util.Constant.Companion.PRODUCT_SKU
import com.mxhung.productdiscoveryandroid.util.Constant.Companion.SEARCH_API
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * REST API access points
 */
interface ProductDiscoveryService {
    @GET(SEARCH_API)
    fun searchRepos(@Query("") query: String): LiveData<ApiResponse<ProductDiscoveryResponse>>

    @GET(DETAIL_API)
    fun getProductDetail(@Path(PRODUCT_SKU) productSku: String): LiveData<ApiResponse<ProductDiscoveryResponse>>
}

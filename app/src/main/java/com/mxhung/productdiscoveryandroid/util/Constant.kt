package com.mxhung.productdiscoveryandroid.util

class Constant {
    companion object{
        const val BASE_URL = "https://listing.stage.tekoapis.net/api/"
        const val SEARCH_API = "search/?channel=pv_showroom&visitorId=&q=&terminal=CP01"
        const val DETAIL_API = "products/{product_sku}?channel=pv_showroom&terminal=CP01"
        const val PRODUCT_SKU = "product_sku"
    }
}
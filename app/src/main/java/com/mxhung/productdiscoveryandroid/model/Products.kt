package com.mxhung.productdiscoveryandroid.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mxhung.productdiscoveryandroid.db.GithubTypeConverters

@Entity(primaryKeys = ["sku"])
@TypeConverters(GithubTypeConverters::class)
data class Products(
    @SerializedName("sku") val sku: Int = 0,
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("seller")
    @Embedded(prefix = "seller_")
    val seller: Seller,
    @SerializedName("brand")
    @Embedded(prefix = "brand_")
    val brand: Brand,
    @SerializedName("status")
    @Embedded(prefix = "status_")
    val status: Status,
    @SerializedName("objective")
    @Embedded(prefix = "objective_")
    val objective: Objective,
    @SerializedName("productType")
    @Embedded(prefix = "productType_")
    val productType: ProductType,
    @SerializedName("images") val images: List<Images>,
    @SerializedName("price") val price: Price,
    @SerializedName("productLine") val productLine: ProductLine,
    @SerializedName("stocks") val stocks: List<String>,
    @SerializedName("totalAvailable") val totalAvailable: Int = 0,
    @SerializedName("isBundle") val isBundle: Boolean,
    @SerializedName("bundleProducts") val bundleProducts: Int = 0,
    @SerializedName("parentBundles") val parentBundles: Int = 0,
    @SerializedName("totalAvailableByStocks") val totalAvailableByStocks: List<TotalAvailableByStocks>,
    @SerializedName("displayName") val displayName: String?,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("promotionPrices") val promotionPrices: List<PromotionPrices>,
    @SerializedName("promotions") val promotions: List<String>,
    @SerializedName("flashSales") val flashSales: List<String>,
    @SerializedName("attributeSet") val attributeSet: AttributeSet,
    @SerializedName("categories") val categories: List<Categories>,
    @SerializedName("magentoId") val magentoId: Int = 0,
    @SerializedName("seoInfo")
    @Embedded(prefix = "seoInfo_")
    val seoInfo: SeoInfo,
    @SerializedName("rating")
    @Embedded(prefix = "rating_")
    val rating: Rating,
    @SerializedName("allActiveFlashSales") val allActiveFlashSales: List<String>
) {
    data class Seller(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("name") val name: String?,
        @SerializedName("displayName") val displayName: String?
    )

    data class Brand(

        @SerializedName("code") val code: String?,
        @SerializedName("name") val name: String?
    )

    data class Status(

        @SerializedName("publish") val publish: Boolean,
        @SerializedName("sale") val sale: String?
    )

    data class Objective(

        @SerializedName("code") val code: String?,
        @SerializedName("name") val name: String?
    )

    data class ProductType(

        @SerializedName("code") val code: String?,
        @SerializedName("name") val name: String?
    )

    data class Price(

        @SerializedName("supplierSalePrice") val supplierSalePrice: Int = 0,
        @SerializedName("sellPrice") val sellPrice: Int = 0
    )

    data class ProductLine(

        @SerializedName("code") val code: String?,
        @SerializedName("name") val name: String?
    )

    data class Color(

        @SerializedName("code") val code: String?,
        @SerializedName("name") val name: String?
    )

    data class PromotionPrices(

        @SerializedName("channel") val channel: String?,
        @SerializedName("terminal") val terminal: String?,
        @SerializedName("finalPrice") val finalPrice: Int = 0,
        @SerializedName("promotionPrice") val promotionPrice: String?,
        @SerializedName("bestPrice") val bestPrice: Int = 0,
        @SerializedName("flashSalePrice") val flashSalePrice: String?
    )

    data class AttributeSet(

        @SerializedName("id") val id: Int = 0,
        @SerializedName("name") val name: String?
    )

    data class Categories(

        @SerializedName("id") val id: Int = 0,
        @SerializedName("code") val code: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("level") val level: Int = 0,
        @SerializedName("parentId") val parentId: Int = 0
    )

    data class SeoInfo(

        @SerializedName("metaKeyword") val metaKeyword: String?,
        @SerializedName("metaTitle") val metaTitle: String?,
        @SerializedName("metaDescription") val metaDescription: String?,
        @SerializedName("shortDescription") val shortDescription: String?,
        @SerializedName("description") val description: String?
    )

    data class Rating(

        @SerializedName("averagePoint") val averagePoint: String?,
        @SerializedName("voteCount") val voteCount: Int = 0
    )

    data class TotalAvailableByStocks (

        val type : String?,
        val total : Int = 0
    )

    data class Images (

        val url : String?,
        val priority : Int = 0,
        val path : String?
    )
}
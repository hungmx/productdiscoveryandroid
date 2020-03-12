package com.mxhung.productdiscoveryandroid.model

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Products(
    @field:SerializedName("sku") val sku: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("seller")
    @field:Embedded(prefix = "seller_")
    val seller: Seller,
    @field:SerializedName("brand")
    @field:Embedded(prefix = "brand_")
    val brand: Brand,
    @field:SerializedName("status")
    @field:Embedded(prefix = "status_")
    val status: Status,
    @field:SerializedName("objective")
    @field:Embedded(prefix = "objective_")
    val objective: Objective,
    @field:SerializedName("productType")
    @field:Embedded(prefix = "productType_")
    val productType: ProductType,
    @field:SerializedName("images") val images: List<String>,
    @field:SerializedName("price") val price: Price,
    @field:SerializedName("productLine") val productLine: ProductLine,
    @field:SerializedName("stocks") val stocks: List<String>,
    @field:SerializedName("totalAvailable") val totalAvailable: String,
    @field:SerializedName("isBundle") val isBundle: Boolean,
    @field:SerializedName("bundleProducts") val bundleProducts: String,
    @field:SerializedName("parentBundles") val parentBundles: String,
    @field:SerializedName("totalAvailableByStocks") val totalAvailableByStocks: List<String>,
    @field:SerializedName("displayName") val displayName: String,
    @field:SerializedName("color") val color: Color,
    @field:SerializedName("tags") val tags: List<String>,
    @field:SerializedName("promotionPrices") val promotionPrices: List<PromotionPrices>,
    @field:SerializedName("promotions") val promotions: List<String>,
    @field:SerializedName("flashSales") val flashSales: List<String>,
    @field:SerializedName("attributeSet") val attributeSet: AttributeSet,
    @field:SerializedName("categories") val categories: List<Categories>,
    @field:SerializedName("magentoId") val magentoId: Int,
    @field:SerializedName("seoInfo")
    @field:Embedded(prefix = "seoInfo_")
    val seoInfo: SeoInfo,
    @field:SerializedName("rating")
    @field:Embedded(prefix = "rating_")
    val rating: Rating,
    @field:SerializedName("allActiveFlashSales") val allActiveFlashSales: List<String>
) {
    data class Seller(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("name") val name: String,
        @field:SerializedName("displayName") val displayName: String
    )

    data class Brand(

        @field:SerializedName("code") val code: String,
        @field:SerializedName("name") val name: String
    )

    data class Status(

        @field:SerializedName("publish") val publish: Boolean,
        @field:SerializedName("sale") val sale: String
    )

    data class Objective(

        @field:SerializedName("code") val code: String,
        @field:SerializedName("name") val name: String
    )

    data class ProductType(

        @field:SerializedName("code") val code: String,
        @field:SerializedName("name") val name: String
    )

    data class Price(

        @field:SerializedName("supplierSalePrice") val supplierSalePrice: Int,
        @field:SerializedName("sellPrice") val sellPrice: Int
    )

    data class ProductLine(

        @field:SerializedName("code") val code: String,
        @field:SerializedName("name") val name: String
    )

    data class Color(

        @field:SerializedName("code") val code: String,
        @field:SerializedName("name") val name: String
    )

    data class PromotionPrices(

        @field:SerializedName("channel") val channel: String,
        @field:SerializedName("terminal") val terminal: String,
        @field:SerializedName("finalPrice") val finalPrice: Int,
        @field:SerializedName("promotionPrice") val promotionPrice: String,
        @field:SerializedName("bestPrice") val bestPrice: Int,
        @field:SerializedName("flashSalePrice") val flashSalePrice: String
    )

    data class AttributeSet(

        @field:SerializedName("id") val id: Int,
        @field:SerializedName("name") val name: String
    )

    data class Categories(

        @field:SerializedName("id") val id: Int,
        @field:SerializedName("code") val code: String,
        @field:SerializedName("name") val name: String,
        @field:SerializedName("level") val level: Int,
        @field:SerializedName("parentId") val parentId: Int
    )

    data class SeoInfo(

        @field:SerializedName("metaKeyword") val metaKeyword: String,
        @field:SerializedName("metaTitle") val metaTitle: String,
        @field:SerializedName("metaDescription") val metaDescription: String,
        @field:SerializedName("shortDescription") val shortDescription: String,
        @field:SerializedName("description") val description: String
    )

    data class Rating(

        @field:SerializedName("averagePoint") val averagePoint: String,
        @field:SerializedName("voteCount") val voteCount: Int
    )
}
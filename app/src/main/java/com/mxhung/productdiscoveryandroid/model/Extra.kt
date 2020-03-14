package com.mxhung.productdiscoveryandroid.model

import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mxhung.productdiscoveryandroid.db.GithubTypeConverters

@Entity(primaryKeys = ["totalItems"])
@TypeConverters(GithubTypeConverters::class)
data class Extra (
    @field:SerializedName("totalItems") val totalItems : Int,
    @field:SerializedName("page") val page : Int,
    @field:SerializedName("pageSize") val pageSize : Int,
    @field:SerializedName("priceRanges") val priceRanges : List<PriceRanges>
){
    data class PriceRanges (

        @field:SerializedName("maxPrice") val maxPrice : Double,
        @field:SerializedName("minPrice") val minPrice : Double
    )
}
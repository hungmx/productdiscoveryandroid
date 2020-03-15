package com.mxhung.productdiscoveryandroid.api

import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mxhung.productdiscoveryandroid.db.ProductDiscoveryTypeConverters
import com.mxhung.productdiscoveryandroid.model.Extra
import com.mxhung.productdiscoveryandroid.model.Result

@Entity(primaryKeys = ["code"])
@TypeConverters(ProductDiscoveryTypeConverters::class)
data class ProductDiscoveryResponse (
	@SerializedName("code") val code : String,
	@SerializedName("result") val result : Result,
	@SerializedName("extra") val extras : Extra
)
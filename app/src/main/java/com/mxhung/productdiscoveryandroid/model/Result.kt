package com.mxhung.productdiscoveryandroid.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity
data class Result (
	@field:SerializedName("products") val products : List<Products>,
	@field:SerializedName("keywords") val keywords : List<String>,
	@field:SerializedName("filters") val filters : List<String>
)
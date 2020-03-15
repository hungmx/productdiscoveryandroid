package com.mxhung.productdiscoveryandroid.model

import com.google.gson.annotations.SerializedName

data class Result (
	@SerializedName("products") val products : List<Products>,
	@SerializedName("keywords") val keywords : List<String>,
	@SerializedName("filters") val filters : List<String>
)
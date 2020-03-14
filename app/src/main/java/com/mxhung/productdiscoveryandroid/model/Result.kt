package com.mxhung.productdiscoveryandroid.model

import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mxhung.productdiscoveryandroid.db.GithubTypeConverters

data class Result (
	@SerializedName("products") val products : List<Products>,
	@SerializedName("keywords") val keywords : List<String>,
	@SerializedName("filters") val filters : List<String>
)
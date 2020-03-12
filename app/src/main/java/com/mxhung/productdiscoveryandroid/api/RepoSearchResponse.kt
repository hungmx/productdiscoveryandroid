package com.mxhung.productdiscoveryandroid.api

import Extras
import com.google.gson.annotations.SerializedName
import com.mxhung.productdiscoveryandroid.model.Result

data class RepoSearchResponse (

	@SerializedName("code") val code : String,
	@SerializedName("result") val result : Result,
	@SerializedName("extra") val extras : Extras
)
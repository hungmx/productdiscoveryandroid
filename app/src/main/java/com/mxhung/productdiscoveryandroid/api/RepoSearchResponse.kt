package com.mxhung.productdiscoveryandroid.api

import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mxhung.productdiscoveryandroid.db.GithubTypeConverters
import com.mxhung.productdiscoveryandroid.model.Extra
import com.mxhung.productdiscoveryandroid.model.Result

@Entity(primaryKeys = ["code"])
@TypeConverters(GithubTypeConverters::class)
data class RepoSearchResponse (
	@SerializedName("code") val code : String,
	@SerializedName("result") val result : Result,
	@SerializedName("extra") val extras : Extra
)
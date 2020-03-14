/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mxhung.productdiscoveryandroid.api

import androidx.lifecycle.LiveData
import com.mxhung.productdiscoveryandroid.util.Constant.Companion.SEARCH_API
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * REST API access points
 */
interface ProductDiscoveryService {
    @GET(SEARCH_API)
    fun searchRepos(@Query("") query: String): LiveData<ApiResponse<RepoSearchResponse>>
//
//    @GET("search/repositories")
//    fun searchRepos(@Query("q") query: String, @Query("page") page: Int): Call<com.mxhung.productdiscoveryandroid.api.RepoSearchResponse>
}

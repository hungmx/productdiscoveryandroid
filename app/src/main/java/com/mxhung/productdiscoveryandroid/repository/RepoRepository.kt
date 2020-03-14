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

package com.mxhung.productdiscoveryandroid.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.mxhung.productdiscoveryandroid.AppExecutors
import com.mxhung.productdiscoveryandroid.api.ApiSuccessResponse
import com.mxhung.productdiscoveryandroid.api.ProductDiscoveryService
import com.mxhung.productdiscoveryandroid.api.RepoSearchResponse
import com.mxhung.productdiscoveryandroid.db.GithubDb
import com.mxhung.productdiscoveryandroid.db.RepoDao
import com.mxhung.productdiscoveryandroid.model.Products
import com.mxhung.productdiscoveryandroid.model.Resource
import com.mxhung.productdiscoveryandroid.model.Result
import com.mxhung.productdiscoveryandroid.util.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles Repo instances.
 *
 * unfortunate naming :/ .
 * Repo - value object name
 * Repository - type of this class.
 */
@Singleton
class RepoRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val db: GithubDb,
    private val repoDao: RepoDao,
    private val githubService: ProductDiscoveryService
) {

    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

   /* fun loadRepos(owner: String): LiveData<Resource<List<Result>>> {
        return object : NetworkBoundResource<List<Result>, List<Result>>(appExecutors) {
            override fun saveCallResult(item: List<Result>) {
                repoDao.insertRepos(item)
            }

            override fun shouldFetch(data: List<Result>?): Boolean {
                return data == null || data.isEmpty() || repoListRateLimit.shouldFetch(owner)
            }

            override fun loadFromDb() = repoDao.loadRepositories(owner)

            override fun createCall() = githubService.getRepos(owner)

            override fun onFetchFailed() {
                repoListRateLimit.reset(owner)
            }
        }.asLiveData()
    }*/

    /*fun loadRepo(owner: String, name: String): LiveData<Resource<Result>> {
        return object : NetworkBoundResource<Result, Result>(appExecutors) {
            override fun saveCallResult(item: Result) {
                repoDao.insert(item)
            }

            override fun shouldFetch(data: Result?) = data == null

            override fun loadFromDb() = repoDao.load(
                ownerLogin = owner,
                name = name
            )

            override fun createCall() = githubService.getRepo(
                owner = owner,
                name = name
            )
        }.asLiveData()
    }*/

   /* fun loadContributors(owner: String, name: String): LiveData<Resource<List<Contributor>>> {
        return object : NetworkBoundResource<List<Contributor>, List<Contributor>>(appExecutors) {
            override fun saveCallResult(item: List<Contributor>) {
                item.forEach {
                    it.repoName = name
                    it.repoOwner = owner
                }
                db.runInTransaction {
                    repoDao.createRepoIfNotExists(
                        Repo(
                            id = Repo.UNKNOWN_ID,
                            name = name,
                            fullName = "$owner/$name",
                            description = "",
                            owner = Repo.Owner(owner, null),
                            stars = 0
                        )
                    )
                    repoDao.insertContributors(item)
                }
            }

            override fun shouldFetch(data: List<Contributor>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb() = repoDao.loadContributors(owner, name)

            override fun createCall() = githubService.getContributors(owner, name)
        }.asLiveData()
    }

    fun searchNextPage(query: String): LiveData<Resource<Boolean>> {
        val fetchNextSearchPageTask = FetchNextSearchPageTask(
            query = query,
            githubService = githubService,
            db = db
        )
        appExecutors.networkIO().execute(fetchNextSearchPageTask)
        return fetchNextSearchPageTask.liveData
    }*/
   /*fun loadRepo(owner: String, name: String): LiveData<Resource<Result>> {
          return object : NetworkBoundResource<Result, Result>(appExecutors) {
              override fun saveCallResult(item: Result) {
                  repoDao.insert(item)
              }

              override fun shouldFetch(data: Result?) = data == null

              override fun loadFromDb() = repoDao.load(
                  ownerLogin = owner,
                  name = name
              )

              override fun createCall() = githubService.getRepo(
                  owner = owner,
                  name = name
              )
          }.asLiveData()
      }*/
    fun search(query: String): LiveData<Resource<List<Products>>> {
        return object : NetworkBoundResource<List<Products>, RepoSearchResponse>(appExecutors) {

            override fun saveCallResult(item: RepoSearchResponse) {
//                val repoIds = item.result.map { it.id }
//                val repoIds = item.products.map { it.sku }
//                val repoSearchResult = RepoSearchResult(
//                    query = query,
//                    repoIds = repoIds,
//                    totalCount = item.total,
//                    next = item.nextPage
//                )
                Log.d("hungmx search ", item.result.products.toString())
                db.runInTransaction {
                    repoDao.insertRepos(item.result.products)
//                    repoDao.insert(repoSearchResult)
                }
            }

            override fun shouldFetch(data: List<Products>?): Boolean{
                return data == null || data.isEmpty() || repoListRateLimit.shouldFetch(query)
            }

            override fun loadFromDb(): LiveData<List<Products>> {
                return repoDao.search()
            }

            override fun createCall() = githubService.searchRepos(query)

            override fun processResponse(response: ApiSuccessResponse<RepoSearchResponse>)
                    : RepoSearchResponse {
                val body = response.body
//                body.nextPage = response.nextPage
                return body
            }

            override fun onFetchFailed() {
                super.onFetchFailed()
                repoListRateLimit.reset(query)
            }
        }.asLiveData()
    }
}

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

package com.mxhung.productdiscoveryandroid.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mxhung.productdiscoveryandroid.model.Products

/**
 * Interface for database access on Repo related operations.
 */
@Dao
abstract class RepoDao {

   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg repos: Repo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertContributors(contributors: List<Contributor>)*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRepos(repositories: List<Products>)

    @Query("SELECT * FROM Products")
    abstract fun search(query: String): LiveData<List<Products>>

   /* @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun createRepoIfNotExists(repo: Repo): Long

    @Query("SELECT * FROM repo WHERE owner_login = :ownerLogin AND name = :name")
    abstract fun load(ownerLogin: String, name: String): LiveData<Repo>

    @Query(
        """
        SELECT login, avatarUrl, repoName, repoOwner, contributions FROM contributor
        WHERE repoName = :name AND repoOwner = :owner
        ORDER BY contributions DESC"""
    )
    abstract fun loadContributors(owner: String, name: String): LiveData<List<Contributor>>

    @Query(
        """
        SELECT * FROM Repo
        WHERE owner_login = :owner
        ORDER BY stars DESC"""
    )
    abstract fun loadRepositories(owner: String): LiveData<List<Repo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(result: RepoSearchResult)

    @Query("SELECT * FROM RepoSearchResult WHERE `query` = :query")
    abstract fun search(query: String): LiveData<RepoSearchResult?>

    fun loadOrdered(repoIds: List<Int>): LiveData<List<Repo>> {
        val order = SparseIntArray()
        repoIds.withIndex().forEach {
            order.put(it.value, it.index)
        }
        return loadById(repoIds).map { repositories ->
            repositories.sortedWith(compareBy { order.get(it.id) })
        }
    }

    @Query("SELECT * FROM Repo WHERE id in (:repoIds)")
    protected abstract fun loadById(repoIds: List<Int>): LiveData<List<Repo>>

    @Query("SELECT * FROM RepoSearchResult WHERE `query` = :query")
    abstract fun findSearchResult(query: String): RepoSearchResult?*/
}

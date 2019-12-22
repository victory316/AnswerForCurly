package com.example.githubissuesearcher.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubissuesearcher.data.local.entity.GithubData

/**
 *  GithubDao
 *
 *  - Room DB와의 Query 수행을 위한 Dao interface
 */
@Dao
interface GithubDao {

    @Query("SELECT * FROM github ORDER BY full_name ASC")
    fun getAll(): LiveData<List<GithubData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(githubData: GithubData)

    @Delete
    fun delete(githubData: GithubData)

    @Query("DELETE FROM github")
    fun deleteAll()
}
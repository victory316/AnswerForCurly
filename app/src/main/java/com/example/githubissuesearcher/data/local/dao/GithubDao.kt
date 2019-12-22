package com.example.githubissuesearcher.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubissuesearcher.data.local.entity.GithubData

// Room DB 구현에 필요한 DAO 클래스
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

//    @Query("UPDATE github SET favorite = (:input) WHERE full_name = (:name)")
//    fun updateColumn(input: Int, name: String)
}
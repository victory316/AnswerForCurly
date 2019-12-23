package com.example.githubissuesearcher.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *  GithubDatabase
 *
 *  - Room 에 저장하기 위해 정의하는 data class
 */
@Entity(tableName= "github")
data class GithubData(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "full_name")
    var full_name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "stargazers_count")
    var stargazers_count: Int,

    @ColumnInfo(name = "language")
    var language: String
)
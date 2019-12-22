package com.example.githubissuesearcher.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "github")
data class GithubData(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "avatar_url")
    var avatar_url: String,

    @ColumnInfo(name = "score")
    var score: Int,

    @ColumnInfo(name = "favorite")
    var favorite: Int
)
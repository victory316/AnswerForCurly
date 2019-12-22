package com.example.githubissuesearcher.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RepositoryList {
    @SerializedName("items")
    @Expose
    lateinit var items: List<GithubRepo>

    fun getRepositoryList(): List<GithubRepo> {
        return items
    }
}
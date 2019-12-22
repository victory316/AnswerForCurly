package com.example.githubissuesearcher.data

import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("full_name") val full_name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val stargazers_count: Int?,
    @SerializedName("language") val language: String?
    )
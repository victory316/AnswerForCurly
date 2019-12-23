package com.example.githubissuesearcher.data

import com.google.gson.annotations.SerializedName

/**
 *  GithubClient
 *
 *  - API로부터 전달받는 데이터를 담기 위한 data class
 */

data class GithubRepo(
    @SerializedName("full_name") val full_name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val stargazers_count: Int?,
    @SerializedName("language") val language: String?
    )
package com.example.githubissuesearcher.data.remote

import com.example.githubissuesearcher.data.RepositoryList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("/search/repositories")
    fun searchUser(@Query("q") users : String,
                   @Query("page") page: Int = 1,
                   @Query("per_page") perPage: Int = 20) : Observable<RepositoryList>
}
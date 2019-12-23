package com.example.githubissuesearcher.data.remote

import com.example.githubissuesearcher.data.RepositoryList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  GithubApi
 *
 *  - /search/repositories 를 통해 검색값으로부터 관련된 json 데이터를 수신
 *  - 페이지 및 아이템의 갯수 설정
 */

interface GithubApi {
    @GET("/search/repositories")
    fun searchUser(@Query("q") users : String,
                   @Query("page") page: Int = 1,
                   @Query("per_page") perPage: Int = 20) : Observable<RepositoryList>
}
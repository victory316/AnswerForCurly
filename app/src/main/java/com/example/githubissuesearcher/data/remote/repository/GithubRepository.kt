package com.example.githubissuesearcher.data.remote.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubissuesearcher.data.local.GithubDatabase
import com.example.githubissuesearcher.data.local.dao.GithubDao
import com.example.githubissuesearcher.data.local.entity.GithubData

/**
 *  GithubApi
 *
 *  - /search/repositories 를 통해 검색값으로부터 관련된 json 데이터를 수신
 *  - 페이지 및 아이템의 갯수 설정
 */

class GithubRepository(application: Application) {

    private val githubDatabase = GithubDatabase.getInstance(application)!!
    private val githubDao: GithubDao = githubDatabase.githubDao()
    private val githubData: LiveData<List<GithubData>> = githubDao.getAll()

    fun getAll(): LiveData<List<GithubData>> {
        return githubData
    }

    fun insert(githubData: GithubData) {
        try {
            val thread = Thread(Runnable {
                githubDao.insert(githubData) })
            thread.start()
        } catch (e: Exception) { }
    }

    fun deleteAll() {
        try {
            val thread = Thread(Runnable {
                githubDao.deleteAll()
            })
            thread.start()
        } catch (e: Exception) { }
    }
}

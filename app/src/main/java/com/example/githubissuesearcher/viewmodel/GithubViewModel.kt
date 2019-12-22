package com.example.githubissuesearcher.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.githubissuesearcher.data.GithubRepo
import com.example.githubissuesearcher.view.activity.MainActivity
import com.example.githubissuesearcher.data.local.entity.GithubData
import com.example.githubissuesearcher.data.remote.repository.GithubRepository

/**
 *  GithubViewModel
 *
 *  - LiveData를 통해 데이터를 가져오고, 검색 및 아이템 추가시의 예외처리 수행
 */

class GithubViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        GithubRepository(
            application
        )
    private val results = repository.getAll()
    private lateinit var githubView: MainActivity

    fun getAll(): LiveData<List<GithubData>> {
        return this.results
    }

    private var fullName = ""
    private var descriptions = ""
    private var stargazersCount = 0
    private var language = ""

    fun insertList(contactList: List<GithubRepo>) {
        for (indices in contactList) {
            fullName = ""
            descriptions = "Not described"
            stargazersCount = 0
            language = "Not described"

            // 공란이 있을 수 있는 자료들에 한해 null check, null일 경우에는 default 값을 적
            if (indices.description != null) descriptions = indices.description
            if (indices.stargazers_count != null) stargazersCount = indices.stargazers_count
            if (indices.language != null) language = indices.language

            val githubData = GithubData(indices.full_name, descriptions, stargazersCount, language)
            repository.insert(githubData)
        }
    }

    fun doSearch() {
        Log.d("clickTest", "click click")
        githubView.doSearch()
    }

    fun setView(view: MainActivity) {
        githubView = view
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}
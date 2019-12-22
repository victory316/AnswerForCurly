package com.example.githubissuesearcher.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.githubissuesearcher.view.activity.MainActivity
import com.example.githubissuesearcher.data.local.entity.GithubData
import com.example.githubissuesearcher.data.remote.repository.GithubRepository

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

    fun insert(contact: GithubData) {
        repository.insert(contact)
    }

    fun insertList(contactList: List<GithubRepository>) {
        for (indices in contactList) {

        }
    }

    fun updateList(input: Int, name: String) {
        repository.update(input, name)
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
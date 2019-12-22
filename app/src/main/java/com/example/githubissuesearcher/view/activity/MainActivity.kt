package com.example.githubissuesearcher.view.activity

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.githubissuesearcher.R
import com.example.githubissuesearcher.data.local.entity.GithubData
import com.example.githubissuesearcher.databinding.ActivityMainBinding
import com.example.githubissuesearcher.view.adapter.GithubSearchAdapter
import com.example.githubissuesearcher.viewmodel.GithubViewModel
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var githubViewModel: GithubViewModel
    private lateinit var githubSearchAdapter: GithubSearchAdapter
    private var searchDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Activity.hideKeyboard() {
        if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
    }

    // ViewModel 설정
    private fun setupViewModel() {
        githubSearchAdapter = GithubSearchAdapter()
        githubSearchAdapter.setView(this)

        githubViewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)
        githubViewModel.deleteAll()

        githubViewModel.getAll().observe(this, Observer<List<GithubData>> { githubData ->
            githubSearchAdapter.setContacts(githubData!!)
        })

        githubViewModel.setView(this)
        githubSearchAdapter.setViewModel(githubViewModel)
    }

    fun doSearch() {
        hideKeyboard()
        Toast.makeText(this, "search!", Toast.LENGTH_SHORT).show()
    }

}
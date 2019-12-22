package com.example.githubissuesearcher.view.activity

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubissuesearcher.R
import com.example.githubissuesearcher.data.local.entity.GithubData
import com.example.githubissuesearcher.data.remote.GithubClient
import com.example.githubissuesearcher.databinding.ActivityMainBinding
import com.example.githubissuesearcher.view.adapter.GithubSearchAdapter
import com.example.githubissuesearcher.viewmodel.GithubViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var githubViewModel: GithubViewModel
    private lateinit var githubSearchAdapter: GithubSearchAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var searchDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupBinding()
        setupView()
    }

    private fun setupView() {
        linearLayoutManager = LinearLayoutManager(this)
        binding.searchRecyclerView.layoutManager = linearLayoutManager
        binding.searchRecyclerView.adapter = githubSearchAdapter
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = githubViewModel
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
        // 키보드 숨
        hideKeyboard()

        val target = binding.searchEditText.text.toString()

        // Gihub search query로 찾고자 하는 유저를 검색
        searchDisposable = GithubClient().getApi().searchUser(target)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                githubViewModel.insertList(result.getRepositoryList())
            }, {
                    error ->
                run {
                    error.printStackTrace()
                    Toast.makeText(this, "결과가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            })

        githubViewModel.deleteAll()
    }

}
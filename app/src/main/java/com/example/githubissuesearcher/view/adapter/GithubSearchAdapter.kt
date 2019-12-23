package com.example.githubissuesearcher.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubissuesearcher.R
import com.example.githubissuesearcher.data.local.entity.GithubData
import com.example.githubissuesearcher.view.activity.MainActivity
import com.example.githubissuesearcher.viewmodel.GithubViewModel

/**
 *  GithubSearchAdapter
 *
 *  - Github RecyclerView의 item 내용 설정
 */

class GithubSearchAdapter : RecyclerView.Adapter<GithubSearchAdapter.ViewHolder>() {
    private var contacts: List<GithubData> = listOf()
    private lateinit var viewModel: GithubViewModel
    private lateinit var view : MainActivity

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.github_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(contacts[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameTv = itemView.findViewById<TextView>(R.id.repository_name)
        private val descriptionTv = itemView.findViewById<TextView>(R.id.repository_description)
        private val starTv = itemView.findViewById<TextView>(R.id.star)
        private val languageTv = itemView.findViewById<TextView>(R.id.language_text_view)

        fun bind(githubData: GithubData) {
            nameTv.text = githubData.full_name
            descriptionTv.text = githubData.description
            starTv.text = githubData.stargazers_count.toString()
            languageTv.text = githubData.language
        }
    }

    fun setView(view: MainActivity) {
        this.view = view
    }

    fun setViewModel(model: GithubViewModel) {
        viewModel = model
    }

    fun setContacts(contacts: List<GithubData>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}
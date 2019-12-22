package com.example.githubissuesearcher.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubissuesearcher.data.local.dao.GithubDao
import com.example.githubissuesearcher.data.local.entity.GithubData

@Database(entities = [GithubData::class], version =1)
abstract class GithubDatabase: RoomDatabase() {

    abstract fun githubDao(): GithubDao

    companion object {
        private var INSTANCE: GithubDatabase? = null

        fun getInstance(context: Context): GithubDatabase? {
            if (INSTANCE == null) {
                synchronized(GithubDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        GithubDatabase::class.java, "github_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
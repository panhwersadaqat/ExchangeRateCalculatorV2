package com.example.mvvmca

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AppClass : Application(){


    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {

        private lateinit var context: Context

        fun getAppContext(): Context {
            return context
        }

    }
}
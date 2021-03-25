package com.exam.janus

import android.app.Application
import com.exam.janus.util.SharedPreferencesHelper

class App: Application() {

    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPreferencesHelper = SharedPreferencesHelper(this)
    }

}
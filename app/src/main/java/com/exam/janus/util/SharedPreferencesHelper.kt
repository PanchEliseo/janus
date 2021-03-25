package com.exam.janus.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    companion object {
        const val NAME_PREFERENCES = "EXAM"
        const val LOGIN = "LOGIN"
    }

    private val sharedPreferences : SharedPreferences =
            context.getSharedPreferences(NAME_PREFERENCES, Context.MODE_PRIVATE)

    fun setLogin(value: Int){
        sharedPreferences.edit().putInt(LOGIN, value).apply()
    }

    fun getLogin(): Int{
        return sharedPreferences.getInt(LOGIN, 0)
    }

}
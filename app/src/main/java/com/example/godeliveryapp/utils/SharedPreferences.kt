package com.example.godeliveryapp.utils

import android.content.Context

class SharedPreferences(private val context: Context) {

    companion object {
        private const val REF_KEY = "REF_KEY"
    }

    fun saveStringData(key: String, data : String?) {

        val sharedPreferences = context.getSharedPreferences(REF_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, data).apply()

    }

    fun getStringData(key: String): String? {

        val sharedPreferences = context.getSharedPreferences(REF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)

    }

    fun clearPreferences() {
        val sharedPreferences = context.getSharedPreferences(REF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.edit().clear().apply()
    }

}
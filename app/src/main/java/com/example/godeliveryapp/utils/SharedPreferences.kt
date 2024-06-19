package com.example.godeliveryapp.utils

import android.content.Context

class SharedPreferences(private val context: Context) {

    companion object {
        private const val SHARED_PREF_FILE = "APP_FILE"
    }

    fun saveTokenData(key: String, data: String?) {

        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, data).apply()

    }

    fun getTokenData(key: String): String? {

        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)

    }

    fun saveUserData(key: String, data: String?) {

        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, data).apply()

    }

    fun getUserData(key: String): String? {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun saveOrderData(key: String, data: UInt?) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt(key, data!!.toInt()).apply()
    }

    fun getOrderData(key: String): Int {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(key, 0)
    }

    fun clearPreferences() {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.edit().clear().apply()
    }

}
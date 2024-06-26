package com.example.godeliveryapp.utils

import android.content.Context

class SharedPreferences(private val context: Context) {

    companion object {
        private const val USER_TOKEN_DETAILS = "USER_TOKEN_DETAILS_FILE"
        private const val MISC_FILE = "MISC_FILE"
        private const val CART_FILE = "CART_FILE"
        private const val SEARCH_HISTORY = "SEARCH_HISTORY_FILE"
    }

    fun saveTokenData(key: String, data: String?) {

        val sharedPreferences = context.getSharedPreferences(USER_TOKEN_DETAILS, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, data).apply()

    }

    fun getTokenData(key: String): String? {

        val sharedPreferences = context.getSharedPreferences(USER_TOKEN_DETAILS, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)

    }

    fun saveUserData(key: String, data: String?) {

        val sharedPreferences = context.getSharedPreferences(USER_TOKEN_DETAILS, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, data).apply()

    }

    fun getUserData(key: String): String? {
        val sharedPreferences = context.getSharedPreferences(USER_TOKEN_DETAILS, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun saveOrderData(key: String, data: UInt?) {
        val sharedPreferences = context.getSharedPreferences(USER_TOKEN_DETAILS, Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt(key, data!!.toInt()).apply()
    }

    fun getOrderData(key: String): Int {
        val sharedPreferences = context.getSharedPreferences(USER_TOKEN_DETAILS, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(key, 0)
    }

    fun clearPreferences() {
        val sharedPreferences = context.getSharedPreferences(USER_TOKEN_DETAILS, Context.MODE_PRIVATE)
        return sharedPreferences.edit().clear().apply()
    }

    fun saveCartData(key: String, data: String?) {

        val sharedPreferences = context.getSharedPreferences(CART_FILE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, data).apply()

    }

    fun getCartData(key: String): String? {
        val sharedPreferences = context.getSharedPreferences(CART_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun clearCartPreferences() {
        val sharedPreferences = context.getSharedPreferences(CART_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.edit().clear().apply()
    }

    fun isFirstTimeOpen(): Boolean {
        val sharedPreferences = context.getSharedPreferences(MISC_FILE, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("IS_FIRST_TIME", true)
    }

    fun setFirstTimeOpen() {
        val sharedPreferences = context.getSharedPreferences(MISC_FILE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("IS_FIRST_TIME", false).apply()
    }

    fun saveSearchHistory(data: List<String>?) {

        val sharedPreferences = context.getSharedPreferences(SEARCH_HISTORY, Context.MODE_PRIVATE)
        sharedPreferences.edit().putStringSet("SEARCH_HISTORY", data?.toSet()).apply()
    }

    fun getSearchHistory(): List<String> {

        val sharedPreferences = context.getSharedPreferences(SEARCH_HISTORY, Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet("SEARCH_HISTORY", emptySet())?.toList() ?: emptyList()

    }

    fun clearSearchHistory() {
        val sharedPreferences = context.getSharedPreferences(SEARCH_HISTORY, Context.MODE_PRIVATE)
        return sharedPreferences.edit().clear().apply()
    }

}
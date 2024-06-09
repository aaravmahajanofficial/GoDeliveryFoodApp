package com.example.godeliveryapp.domain.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.presentation.onBoarding.components.UserState
import com.example.godeliveryapp.utils.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

@HiltViewModel
class SupabaseAuthViewModel @Inject constructor(
    private val supabaseClient: SupabaseClient,
) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Empty)
    val userState: Flow<UserState> get() = _userState

    private fun saveToken(context: Context) {

        viewModelScope.launch {

            val accessToken = supabaseClient.auth.currentAccessTokenOrNull()
            val sharedPref = SharedPreferences(context)
            sharedPref.saveStringData("ACCESS_TOKEN", accessToken)

        }

    }

    private fun getToken(context: Context): String? {


        val sharedPref = SharedPreferences(context)
        return sharedPref.getStringData("ACCESS_TOKEN")


    }

    fun signUp(
        context: Context,
        userEmail: String,
        userPassword: String,
        userName: String
    ) {

        viewModelScope.launch {

            try {

                _userState.value = UserState.Loading
                supabaseClient.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                    data = buildJsonObject { put("displayName", userName) }
                }

                saveToken(context)

                _userState.value = UserState.Success

            } catch (e: Exception) {

                when (e) {

                    is RestException -> {
                        _userState.value = UserState.Error(e.error)
                    }

                    else -> {
                        _userState.value = UserState.Error(e.message.toString())
                    }
                }
            }


        }
    }

    fun login(
        context: Context,
        userEmail: String,
        userPassword: String
    ) {
        viewModelScope.launch {

            try {
                _userState.value = UserState.Loading
                supabaseClient.auth.signInWith(Email) {
                    email = userEmail
                    password = userPassword
                }

                saveToken(context)
                _userState.value = UserState.Success

            } catch (e: Exception) {

                when (e) {

                    is RestException -> {
                        _userState.value = UserState.Error(e.error)
                    }

                    else -> {
                        _userState.value = UserState.Error(e.message.toString())
                    }
                }
            }

        }


    }

    fun logOut(context: Context) {
        val sharedPreferences = SharedPreferences(context)
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                supabaseClient.auth.signOut()
                sharedPreferences.clearPreferences()
                _userState.value = UserState.Success
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message.toString())
            }
        }


    }

    fun forgotPassword(email: String) {

        viewModelScope.launch {

            try {
                _userState.value = UserState.Loading
                supabaseClient.auth.resetPasswordForEmail(email)
                _userState.value = UserState.Success
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message.toString())
            }
        }

    }

    fun isUserLoggedIn(context: Context) {

        viewModelScope.launch {

            try {
                _userState.value = UserState.Loading
                val accessToken = getToken(context)
                if (accessToken.isNullOrEmpty()) {
                    _userState.value = UserState.Error("User not logged in")
                } else {

                    supabaseClient.auth.retrieveUser(accessToken)
                    supabaseClient.auth.refreshCurrentSession()
                    saveToken(context)
                    _userState.value = UserState.Success

                }

            } catch (e: RestException) {
                _userState.value = UserState.Error(e.message.toString())

            }


        }


    }

    fun resetUserState() {
        _userState.value = UserState.Empty
    }


}

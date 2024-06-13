package com.example.godeliveryapp.domain.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.UserDto
import com.example.godeliveryapp.domain.repository.Repository
import com.example.godeliveryapp.presentation.onBoarding.components.UserState
import com.example.godeliveryapp.utils.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.OtpType
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
    private val repository: Repository
) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Empty)
    val userState: Flow<UserState> get() = _userState

    private fun saveToken(context: Context) {

        viewModelScope.launch {

            val accessToken = supabaseClient.auth.currentAccessTokenOrNull()
            val sharedPref = SharedPreferences(context)
            sharedPref.saveTokenData("ACCESS_TOKEN", accessToken)

        }

    }

    private fun getToken(context: Context): String? {

        val sharedPref = SharedPreferences(context)
        return sharedPref.getTokenData("ACCESS_TOKEN")

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

                val sharedPreferences = SharedPreferences(context)
                val user = supabaseClient.auth.retrieveUserForCurrentSession()
                sharedPreferences.saveUserData("USER_NAME", userName)
                sharedPreferences.saveUserData("USER_EMAIL", userEmail)
                sharedPreferences.saveUserData("USER_ID", user.id)

                repository.insertUserData()

                Log.d("USER_ID", user.id)
                Log.d("USER_NAME", userName)
                Log.d("USER_EMAIL", userEmail)



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

    fun forgotPassword(userEmail: String) {

        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                supabaseClient.auth.resetPasswordForEmail(userEmail)
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

    fun verifyOtp(context: Context, userEmail: String, token: String) {

        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                supabaseClient.auth.verifyEmailOtp(
                    type = OtpType.Email.EMAIL,
                    email = userEmail,
                    token = token
                )
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

    fun createNewPassword(context: Context, userPassword: String) {

        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                supabaseClient.auth.modifyUser(updateCurrentUser = true) { password = userPassword }
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

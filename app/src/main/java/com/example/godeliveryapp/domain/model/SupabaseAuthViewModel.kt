package com.example.godeliveryapp.domain.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.UserDto
import com.example.godeliveryapp.domain.repository.Repository
import com.example.godeliveryapp.utils.SharedPreferences
import com.example.godeliveryapp.utils.ViewState
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
    private val repository: Repository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Empty)
    val viewState: Flow<ViewState> get() = _viewState

    private fun saveToken(context: Context) {

        viewModelScope.launch {

            val accessToken = supabaseClient.auth.currentAccessTokenOrNull()
            sharedPreferences.saveTokenData("ACCESS_TOKEN", accessToken)

        }

    }

    private fun getToken(context: Context): String? {

        return sharedPreferences.getTokenData("ACCESS_TOKEN")

    }

    fun signUp(
        context: Context,
        userEmail: String,
        userPassword: String,
        userName: String
    ) {
        viewModelScope.launch {
            try {

                _viewState.value = ViewState.Loading
                supabaseClient.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                    data = buildJsonObject { put("displayName", userName) }
                }

                saveToken(context)

                val user = supabaseClient.auth.retrieveUserForCurrentSession()

                val userDto = UserDto(
                    userId = user.id,
                    userEmail = userEmail,
                    userName = userName,
                    userAddress = "",
                    landmark = ""
                )

                repository.upsertUserData(userDto)

                sharedPreferences.saveUserData("USER_ID", user.id)
                sharedPreferences.saveUserData("USER_NAME", userName)
                sharedPreferences.saveUserData("USER_EMAIL", userEmail)

                _viewState.value = ViewState.Success

            } catch (e: Exception) {

                when (e) {

                    is RestException -> {
                        _viewState.value = ViewState.Error(e.error)
                    }

                    else -> {
                        _viewState.value = ViewState.Error(e.message.toString())
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
                _viewState.value = ViewState.Loading
                supabaseClient.auth.signInWith(Email) {
                    email = userEmail
                    password = userPassword
                }

                saveToken(context)

                val userDto = repository.getUserDataByEmail(userEmail)

                sharedPreferences.saveUserData("USER_ID", userDto.userId)
                sharedPreferences.saveUserData("USER_NAME", userDto.userName)
                sharedPreferences.saveUserData("USER_EMAIL", userDto.userEmail)

                _viewState.value = ViewState.Success

            } catch (e: Exception) {

                when (e) {

                    is RestException -> {
                        _viewState.value = ViewState.Error(e.error)
                    }

                    else -> {
                        _viewState.value = ViewState.Error(e.message.toString())
                    }
                }
            }

        }


    }

    fun logOut(context: Context) {
        viewModelScope.launch {
            try {
                _viewState.value = ViewState.Loading
                supabaseClient.auth.signOut()
                sharedPreferences.clearPreferences()
                sharedPreferences.clearSearchHistory()
                sharedPreferences.clearCartPreferences()
                _viewState.value = ViewState.Success
            } catch (e: Exception) {
                _viewState.value = ViewState.Error(e.message.toString())
            }
        }


    }

    fun forgotPassword(userEmail: String) {

        viewModelScope.launch {
            try {
                _viewState.value = ViewState.Loading
                supabaseClient.auth.resetPasswordForEmail(userEmail)
                _viewState.value = ViewState.Success
            } catch (e: Exception) {
                when (e) {
                    is RestException -> {
                        _viewState.value = ViewState.Error(e.error)
                    }

                    else -> {
                        _viewState.value = ViewState.Error(e.message.toString())

                    }
                }
            }
        }

    }

    fun verifyOtp(context: Context, userEmail: String, token: String) {

        viewModelScope.launch {
            try {
                _viewState.value = ViewState.Loading
                supabaseClient.auth.verifyEmailOtp(
                    type = OtpType.Email.EMAIL,
                    email = userEmail,
                    token = token
                )
                _viewState.value = ViewState.Success

            } catch (e: Exception) {

                when (e) {
                    is RestException -> {
                        _viewState.value = ViewState.Error(e.error)
                    }

                    else -> {
                        _viewState.value = ViewState.Error(e.message.toString())
                    }
                }

            }
        }
    }

    fun createNewPassword(context: Context, userPassword: String) {

        viewModelScope.launch {
            try {
                _viewState.value = ViewState.Loading
                supabaseClient.auth.modifyUser(updateCurrentUser = true) { password = userPassword }
                _viewState.value = ViewState.Success

            } catch (e: Exception) {
                when (e) {
                    is RestException -> {
                        _viewState.value = ViewState.Error(e.error)
                    }

                    else -> {
                        _viewState.value = ViewState.Error(e.message.toString())
                    }
                }
            }
        }


    }

    fun isUserLoggedIn(context: Context) {
        viewModelScope.launch {

            try {
                _viewState.value = ViewState.Loading
                val accessToken = getToken(context)
                if (accessToken.isNullOrEmpty()) {
                    _viewState.value = ViewState.Error("User not logged in")
                } else {

                    supabaseClient.auth.retrieveUser(accessToken)
                    supabaseClient.auth.refreshCurrentSession()
                    saveToken(context)
                    _viewState.value = ViewState.Success

                }

            } catch (e: RestException) {
                _viewState.value = ViewState.Error(e.message.toString())

                sharedPreferences.clearPreferences()
                sharedPreferences.clearCartPreferences()
                sharedPreferences.clearSearchHistory()
            }


        }


    }

    fun resetUserState() {
        _viewState.value = ViewState.Empty
    }


}

package com.example.godeliveryapp.presentation.detailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.domain.repository.Repository
import com.example.godeliveryapp.presentation.detailsScreen.menuItems.MenuItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _menuItems = MutableStateFlow<List<MenuItemModel>>(listOf())
    val menuItems: Flow<List<MenuItemModel>> get() = _menuItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> get() = _isLoading

    private val _filteredList = MutableStateFlow<List<MenuItemModel>>(listOf())
    val filteredList: Flow<List<MenuItemModel>> get() = _filteredList

    var isVeg = MutableStateFlow(false)

    fun getMenu(restaurantId: Int) {

        viewModelScope.launch {

            try {
                _isLoading.emit(true)

                val menuItems = repository.getMenu(restaurantId)
                _menuItems.emit(menuItems)

                _isLoading.emit(false)
            } catch (e: Exception) {
                _isLoading.emit(true)
            }
        }

    }

    fun applyFilter() {

        viewModelScope.launch {

            val filteredRestaurants = _menuItems.value.filter { item ->

                val isVeg = !isVeg.value || item.isVeg

                isVeg

            }

            _filteredList.emit(filteredRestaurants)


        }


    }

}
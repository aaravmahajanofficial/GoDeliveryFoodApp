package com.example.godeliveryapp.presentation.detailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.domain.repository.Repository
import com.example.godeliveryapp.presentation.detailsScreen.menuItems.MenuItemCardModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _menuItems = MutableStateFlow<List<MenuItemCardModel>>(listOf())
    val menuItems: Flow<List<MenuItemCardModel>> get() = _menuItems


    fun getMenu(restaurantId : Int) {

        viewModelScope.launch {

            val menuItems = repository.getMenu(restaurantId)
            val menuItemCard = menuItems.map { menuItem ->

                MenuItemCardModel(
                    itemId = menuItem.itemId,
                    itemName = menuItem.itemName,
                    itemDescription = menuItem.description,
                    itemPrice = menuItem.price,
                )

            }

            _menuItems.emit(menuItemCard)

        }

    }

}
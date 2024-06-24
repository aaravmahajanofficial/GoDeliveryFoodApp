package com.example.godeliveryapp.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.godeliveryapp.data.remote.dataTransferObject.CategoryDto
import com.example.godeliveryapp.domain.model.RestaurantListingCardModel
import com.example.godeliveryapp.domain.repository.Repository
import com.example.godeliveryapp.presentation.detailsScreen.menuItems.MenuItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MergeModel(
    val restaurant: RestaurantListingCardModel,
    val menuItems: List<MenuItemModel>
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _restaurants = MutableStateFlow<List<RestaurantListingCardModel>?>(listOf())
    val restaurants: Flow<List<RestaurantListingCardModel>?> get() = _restaurants

    private val _categories = MutableStateFlow<List<CategoryDto>?>(listOf())
    val categories: Flow<List<CategoryDto>?> get() = _categories

    private val _filterList = MutableStateFlow<List<MergeModel>?>(emptyList())
    val filterList: Flow<List<MergeModel>?> get() = _filterList

    private val _mergeList = MutableStateFlow<List<MergeModel>?>(listOf())

    private val _isLoading = MutableStateFlow(true)

    val isLoading: Flow<Boolean> = _isLoading


    init {
        fetchRestaurants()
        getCategories()
    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val results = repository.getRestaurants()
                _restaurants.emit(results)
                mergeFunc()
                _isLoading.value = false
            } catch (e: Exception) {
//               handle error
            }

        }
    }

    private fun mergeFunc() {

        viewModelScope.launch {

            val mergeList = _restaurants.value?.map {

                val menuItems = repository.getMenu(it.restaurantId)

                MergeModel(it, menuItems)

            }

            _mergeList.emit(mergeList)

        }

    }

    private fun getCategories() {

        viewModelScope.launch {

            val categoryList = repository.getCategories()

            _categories.emit(categoryList)

        }

    }

    fun searchKeyWord(searchTerm: String) {

        val lowerCaseSearchTerm = searchTerm.lowercase()

        viewModelScope.launch {
            val filteredList = _mergeList.value?.filter { item ->
                item.restaurant.name.contains(
                    lowerCaseSearchTerm,
                    ignoreCase = true
                ) || item.restaurant.cuisines.any { cuisine ->
                    cuisine.contains(
                        lowerCaseSearchTerm,
                        ignoreCase = true
                    )
                } || item.restaurant.features.any { feature ->
                    feature.contains(
                        lowerCaseSearchTerm,
                        ignoreCase = true
                    )
                } || item.restaurant.meals.any { meal ->
                    meal.contains(
                        lowerCaseSearchTerm,
                        ignoreCase = true
                    )
                } || item.menuItems.any {
                    it.itemName.contains(
                        lowerCaseSearchTerm,
                        ignoreCase = true
                    )
                }

            }
            _filterList.emit(filteredList)
        }


    }

}
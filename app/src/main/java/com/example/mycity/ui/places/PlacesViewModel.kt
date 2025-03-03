package com.example.mycity.ui.places

import androidx.lifecycle.ViewModel
import com.example.mycity.model.CategoryInfo
import com.example.mycity.model.PlaceInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PlacesViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        PlaceUiState(
            placesList = emptyList(),
            currentCategory = null
        )
    )
    val uiState: StateFlow<PlaceUiState> = _uiState

    fun updateListPlaces(category: CategoryInfo){
        _uiState.update{
            it.copy(
                placesList = category.places,
                currentCategory = category
                )
            }
        }
    }

    data class PlaceUiState(
        val placesList: List<PlaceInfo>,
        val currentCategory: CategoryInfo?,
        val isShowingListPage: Boolean = true
    )
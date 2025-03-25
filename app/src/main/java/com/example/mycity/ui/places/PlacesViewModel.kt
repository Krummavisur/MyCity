package com.example.mycity.ui.places

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mycity.data.DataSource
import com.example.mycity.model.CategoryInfo
import com.example.mycity.model.PlaceInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class PlacesViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val _uiState: MutableStateFlow<PlaceUiState> = MutableStateFlow(PlaceUiState())
    val uiState: StateFlow<PlaceUiState> = _uiState

    val index: Int = savedStateHandle["index"] ?:0

    fun updateListPlaces(categoryIndex: Int){
        val category = DataSource.getPlaces(categoryIndex)
        _uiState.value = PlaceUiState(
               placesList = category.places,
            )
        }
    }

    data class PlaceUiState(
        val placesList: List<PlaceInfo> = emptyList(),
        val currentCategory: CategoryInfo? = null,
        val isShowingListPage: Boolean = true
    )
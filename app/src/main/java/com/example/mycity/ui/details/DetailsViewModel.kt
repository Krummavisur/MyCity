package com.example.mycity.ui.details

import androidx.lifecycle.ViewModel
import com.example.mycity.model.PlaceInfo
import com.example.mycity.ui.places.PlacesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel(
    private val placesViewModel: PlacesViewModel,
    private val placeIndex: Int
        ): ViewModel() {
    private val _uiState = MutableStateFlow<PlaceInfo?>(null)

    val uiState: StateFlow<PlaceInfo?> = _uiState

    init {
        loadPLaceDetails()
    }

    private fun loadPLaceDetails() {
        val place =
            placesViewModel.uiState.value.placesList.getOrNull(placeIndex)

            _uiState.update { place }
    }
}


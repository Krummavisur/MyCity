package com.example.mycity.ui.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(

    )
    val uiState: StateFlow<>
}
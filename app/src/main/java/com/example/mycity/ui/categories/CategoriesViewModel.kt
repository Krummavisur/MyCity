package com.example.mycity.ui.categories

import androidx.lifecycle.ViewModel
import com.example.mycity.data.DataSource
import com.example.mycity.model.CategoryInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoriesViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        CategoryUiState(
            categoryList = DataSource.getCategoryData(),
            currentCategory = DataSource.getCategoryData().getOrElse(0){
                DataSource.defaultCategory
            }
        )
    )
    val uiState: StateFlow<CategoryUiState> = _uiState
}

data class CategoryUiState(
    val categoryList: List<CategoryInfo> =  emptyList(),
    val currentCategory: CategoryInfo = DataSource.defaultCategory,
    val isShowingListPage: Boolean = true
)
package com.example.mycity.ui.categories

import androidx.lifecycle.ViewModel
import com.example.mycity.data.DataSource
import com.example.mycity.model.CategoryInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

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

    fun updateCurrentCategory(selectedCategory: CategoryInfo){
        _uiState.update {
            it.copy(currentCategory = selectedCategory)
        }
    }

    fun navigateToListPage(){
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }

}

data class CategoryUiState(
    val categoryList: List<CategoryInfo> =  emptyList(),
    val currentCategory: CategoryInfo = DataSource.defaultCategory,
    val isShowingListPage: Boolean = true
)
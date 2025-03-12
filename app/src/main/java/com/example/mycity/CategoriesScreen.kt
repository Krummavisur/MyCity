package com.example.mycity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycity.ui.categories.CategoriesViewModel
import com.example.mycity.ui.theme.MyCityTheme

@Composable
fun CategoriesScreen(
    categoriesViewModel: CategoriesViewModel = viewModel(),
    onCategoryClick: () -> Unit
) {
    val uiState by categoriesViewModel.uiState.collectAsState()

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            uiState.categoryList.forEachIndexed {index, category ->
                CustomButton(
                    onClick = {onCategoryClick()},
                    buttonTitle = stringResource(id = category.title),
                    modifier = Modifier.padding(vertical = 20.dp)
                )
            }
        }
    }
}

@Composable
private fun CustomButton(
    onClick: () -> Unit,
    buttonTitle: String,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier
            .height(66.dp)
            .clip(RoundedCornerShape(34.dp))
            .width(250.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
    ) {
        Text(
            text = buttonTitle,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun CategoriesScreenPreview() {
    MyCityTheme {
        CategoriesScreen(
            onCategoryClick = {}
        )
    }
}
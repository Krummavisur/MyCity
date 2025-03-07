package com.example.mycity

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.ui.categories.CategoriesViewModel
import com.example.mycity.ui.places.PlacesViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource

enum class MyCityScreen(@StringRes val title: Int) {
    Categories(title = R.string.app_name),
    Places(title = R.string.places)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
fun MyCityApp() {

    val navController = rememberNavController()

    val categoriesViewModel: CategoriesViewModel = viewModel()

    val placesViewModel: PlacesViewModel = viewModel()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Categories.name)

    Scaffold(
        topBar = {
            MyCityAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Categories.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.Categories.name) {
                CategoriesScreen(
                    navController = navController, categoriesViewModel = categoriesViewModel,
                    placesViewModel = placesViewModel)
            }

            composable(route = MyCityScreen.Places.name) {
                PlacesScreen(
                    placesViewModel = placesViewModel
                )
            }
        }
    }
}
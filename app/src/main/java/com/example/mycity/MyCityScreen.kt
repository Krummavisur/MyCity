package com.example.mycity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.ui.categories.CategoriesViewModel
import com.example.mycity.ui.places.PlacesViewModel

enum class MyCityScreen {
    Categories,
    Places
}


@Composable
fun MyCityApp(){

    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route?: MyCityScreen.Categories.name
    )

    val categoriesViewModel: CategoriesViewModel = viewModel()

    val placesViewModel: PlacesViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = MyCityScreen.Categories.name
    ){
        composable(route = MyCityScreen.Categories.name){
            CategoriesScreen(
                categoriesViewModel = categoriesViewModel
            )
        }
        composable(route = MyCityScreen.Places.name) {
            PlacesScreen(
                placesViewModel = placesViewModel
            )
        }
    }
}
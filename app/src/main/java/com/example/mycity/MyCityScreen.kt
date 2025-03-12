package com.example.mycity

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

enum class MyCityScreen(val route: String, @StringRes val title: Int) {
    Categories("categories", title = R.string.app_name),
    Places("places/{index}", title = R.string.places);

    companion object {
        fun fromRoute(route: String?): MyCityScreen =
            values().firstOrNull { route?.startsWith(it.route) == true }
                ?: Categories
    }
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
fun MyCityApp(
    navController: NavHostController = rememberNavController(),
    categoriesViewModel: CategoriesViewModel = viewModel(),
    placesViewModel: PlacesViewModel = viewModel()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = MyCityScreen.fromRoute(backStackEntry?.destination?.route)

    Scaffold(
        topBar = {
            MyCityAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by categoriesViewModel.uiState.collectAsState()


        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Categories.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.Categories.route) {
                    backStackEntry ->

                CategoriesScreen(
                    categoriesViewModel = categoriesViewModel,
                    onCategoryClick = {
                        index ->
                        Log.d("DEBUG", "test1")

                        navController.navigate(MyCityScreen.Places.route.replace("{index}",index.toString()))
                        Log.d("DEBUG", "testX")
                    }
                )
            }

            composable(
                route = MyCityScreen.Places.route,
                arguments = listOf(navArgument("index") {type = NavType.IntType}))
             {
                     backStackEntry ->

                Log.d("DEBUG", "test2")
               val index =  backStackEntry.arguments?.getInt("index") ?: 0
                Log.d("DEBUG", "test3")
                PlacesScreen(
                    index,
                    placesViewModel = placesViewModel,
                )
            }
        }
    }
}
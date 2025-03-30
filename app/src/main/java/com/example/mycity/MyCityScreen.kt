package com.example.mycity

import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycity.ui.DetailsScreen
import com.example.mycity.ui.categories.CategoriesViewModel
import com.example.mycity.ui.details.DetailsViewModel
import com.example.mycity.ui.details.DetailsViewModelFactory
import com.example.mycity.ui.places.PlacesViewModel

enum class MyCityScreen(val route: String, @StringRes val title: Int) {
    Categories("categories", title = R.string.app_name),
    Places("places/{index}", title = R.string.places),
    Details("details/{index}", title = R.string.details);

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
    placesViewModel: PlacesViewModel = viewModel(),
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

        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Categories.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { fadeIn(animationSpec = tween(0)) } ,
            exitTransition = { fadeOut(animationSpec = tween(0)) },
            popEnterTransition = { fadeIn(animationSpec = tween(0)) },
            popExitTransition = { fadeOut(animationSpec = (tween(0))) },

        ) {
            composable(route = MyCityScreen.Categories.route,

            ) {
                    backStackEntry ->

                CategoriesScreen(
                    categoriesViewModel = categoriesViewModel,
                    onCategoryClick = {
                        index ->
                        navController.navigate("places/$index")
                    }
                )
            }

            composable(
                route = MyCityScreen.Places.route,
                arguments = listOf(navArgument("index") {type = NavType.IntType})
            )
             {
                backStackEntry ->

               val index =  backStackEntry.arguments?.getInt("index") ?: 0

                PlacesScreen(
                    placesViewModel = placesViewModel,
                    navBackStackEntry = backStackEntry,
                    onPlaceClick = {
                        placeIndex ->
                        navController.navigate("details/$placeIndex")
                    }

                )
            }

            composable(
                route = MyCityScreen.Details.route,
                arguments = listOf(navArgument("index") {type = NavType.IntType})
            ) {
                backStackEntry ->
                val index = backStackEntry.arguments?.getInt("index") ?: 0
                val placesViewModel: PlacesViewModel = hiltViewModel()
                val detailsViewModel: DetailsViewModel = viewModel(
                    factory = DetailsViewModelFactory(placesViewModel, index)
                )
                DetailsScreen(
                    detailsViewModel = detailsViewModel
                )

            }
        }
    }
}
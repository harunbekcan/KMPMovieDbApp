package com.harunbekcan.kmpmoviedbapp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.harunbekcan.kmpmoviedbapp.android.common.Detail
import com.harunbekcan.kmpmoviedbapp.android.common.Home
import com.harunbekcan.kmpmoviedbapp.android.common.MovieAppBar
import com.harunbekcan.kmpmoviedbapp.android.common.movieDestinations
import com.harunbekcan.kmpmoviedbapp.android.detail.DetailScreen
import com.harunbekcan.kmpmoviedbapp.android.detail.DetailViewModel
import com.harunbekcan.kmpmoviedbapp.android.home.HomeScreen
import com.harunbekcan.kmpmoviedbapp.android.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val snackbarHostState = remember { SnackbarHostState() }

    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.Transparent
    }
    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinations.find {
        backStackEntry?.destination?.route == it.route ||
                backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Home

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            MovieAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            ) {
                navController.navigateUp()
            }
        }
    ) { innerPaddings ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = Home.routeWithArgs
        ) {
            composable(Home.routeWithArgs) {
                val homeViewModel: HomeViewModel = koinViewModel()
                HomeScreen(
                    uiState = homeViewModel.uiState,
                    loadNextMovies = {
                        homeViewModel.loadMovies(forceReload = it)
                    },
                    navigateToDetail = {
                        navController.navigate(
                            "${Detail.route}/${it.id}"
                        )
                    }
                )
            }

            composable(Detail.routeWithArgs, arguments = Detail.arguments) {
                val movieId = it.arguments?.getInt("movieId") ?: 0
                val detailViewModel: DetailViewModel = koinViewModel(
                    parameters = { parametersOf(movieId.toInt()) }
                )

                DetailScreen(uiState = detailViewModel.uiState)
            }
        }

    }
}
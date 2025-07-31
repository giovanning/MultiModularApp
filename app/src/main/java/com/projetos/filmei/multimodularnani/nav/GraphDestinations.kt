package com.projetos.filmei.multimodularnani.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.siag.navigator.core.AppNavigator
import br.com.siag.navigator.destinations.HomeDestination
import br.com.siag.navigator.destinations.LoginDestination
import br.com.siag.navigator.destinations.NavigationDestination
import br.com.siag.navigator.destinations.SignUpDestination
import br.com.siag.signup.SignUpScreen
import com.projetos.filmei.home.homeScreen
import com.projetos.filmei.login.presentation.view.LoginScreen

private val composableDestinations: Map<NavigationDestination, @Composable (AppNavigator, NavHostController) -> Unit> =
    mapOf(
        SignUpDestination() to { _, _ -> SignUpScreen() },
        HomeDestination to { _, navHostController -> homeScreen(navHostController) },
        LoginDestination() to { appNavigator, _ -> LoginScreen(appNavigator = appNavigator) },
    )

fun NavGraphBuilder.addComposableDestinations(
    appNavigator: AppNavigator,
    navHostController: NavHostController,
) {
    composableDestinations.forEach { entry ->
        val destination = entry.key
        composable(
            route = destination.route(),
            arguments = destination.arguments,
            deepLinks = destination.deepLinks,
        ) {
            entry.value(appNavigator, navHostController)
        }
    }
}

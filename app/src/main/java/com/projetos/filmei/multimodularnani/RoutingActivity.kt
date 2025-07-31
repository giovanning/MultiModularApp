package com.projetos.filmei.multimodularnani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.siag.navigator.core.AppNavigator
import br.com.siag.navigator.destinations.Screens
import br.com.siag.navigator.event.NavigatorEvent
import com.projetos.filmei.multimodularnani.nav.addComposableDestinations
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoutingActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            appScaffold(appNavigator)
        }
    }

    @Composable
    fun appScaffold(appNavigator: AppNavigator) {
        val navController = rememberNavController()

        LaunchedEffect(navController) {
            appNavigator.destinations.collect { event ->

                when (event) {
                    is NavigatorEvent.Directions -> {
                        navController.navigate(
                            event.destination,
                            event.builder,
                        )
                    }

                    NavigatorEvent.NavigateUp -> {
                        navController.navigateUp()
                    }

                    NavigatorEvent.PopBackStack -> {
                        navController.popBackStack()
                    }
                }
            }
        }

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = Screens.LoginScreenRoute.route,
                enterTransition = { fadeIn(animationSpec = tween(500)) },
                exitTransition = { fadeOut(animationSpec = tween(500)) },
            ) {
                addComposableDestinations(appNavigator, navController)
            }
        }
    }
}

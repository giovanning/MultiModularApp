package br.com.siag.navigator.core

import androidx.navigation.NavOptionsBuilder
import br.com.siag.navigator.event.NavigatorEvent
import kotlinx.coroutines.flow.Flow

interface AppNavigator {
    fun navigateUp(): Boolean

    fun popBackStack()

    fun navigate(
        destination: String,
        builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true },
    ): Boolean

    val destinations: Flow<NavigatorEvent>
}

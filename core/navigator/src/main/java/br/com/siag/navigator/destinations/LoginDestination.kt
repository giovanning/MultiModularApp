package br.com.siag.navigator.destinations

import androidx.navigation.NamedNavArgument
const val LOGIN_ROUTE = "LoginRoute"

class LoginDestination : NavigationDestination {
    override fun route(): String = Screens.LoginScreenRoute.route

    override val arguments: List<NamedNavArgument>
        get() = listOf() // pass any argument needed
}

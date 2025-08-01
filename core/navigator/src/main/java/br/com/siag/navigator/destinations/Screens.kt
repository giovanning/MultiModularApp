package br.com.siag.navigator.destinations

sealed class Screens(val route: String) {
    data object LoginScreenRoute : Screens(route = LOGIN_ROUTE)
    data object SignUpScreenRoute : Screens(route = SIGNUP_ROUTE)
    data object HomeScreenRoute :
        Screens(route = "$HOME_ROUTE/{$USER_PARAM}/{$NAME_PARAM}/{$AGE_PARAM}")
}

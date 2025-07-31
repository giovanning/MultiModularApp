package br.com.siag.navigator.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

const val USER_PARAM = "user"
const val NAME_PARAM = "name"
const val AGE_PARAM = "age"
const val HOME_ROUTE = "Home"

object HomeDestination : NavigationDestination {

    fun createHomeRoute(user: String, name: String, age: Int): String =
        "$HOME_ROUTE/$user/$name/$age"

    override fun route(): String = Screens.HomeScreenRoute.route

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(USER_PARAM) { type = NavType.StringType },
            navArgument(NAME_PARAM) { type = NavType.StringType },
            navArgument(AGE_PARAM) { type = NavType.IntType },
        )
}

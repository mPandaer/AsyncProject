package space.pandaer.asyncproject.navigationmoudel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import space.pandaer.asyncproject.frontmoudel.ui.FrontScreen
import space.pandaer.asyncproject.frontmoudel.ui.FrontScreenEvent
import space.pandaer.asyncproject.usermoudel.ui.UserScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation() {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = "frontScreen") {
        composable("frontScreen") {
            FrontScreen{ event ->
                when(event) {
                    FrontScreenEvent.NavigationToUserEvent -> controller.navigate("userScreen")
                    is FrontScreenEvent.NavigationToArticleDetailEvent -> {}
                }

            }
        }
        composable("userScreen") {
            UserScreen()
        }
    }




}
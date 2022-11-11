package space.pandaer.asyncproject.navigationmoudel

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import space.pandaer.asyncproject.frontmoudel.ArticleViewModel
import space.pandaer.asyncproject.frontmoudel.model.*
import space.pandaer.asyncproject.frontmoudel.ui.*
import space.pandaer.asyncproject.usermoudel.ui.UserScreen
import space.pandaer.asyncproject.util.test.defaultAvatarUrl
import space.pandaer.asyncproject.util.test.testArticle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation() {
    val controller = rememberNavController()
    val viewModel = viewModel(ArticleViewModel::class.java)
    NavHost(navController = controller, startDestination = "frontScreen") {
        composable("frontScreen") {
            FrontScreen(viewModel = viewModel) { event ->
                when (event) {
                    FrontScreenEvent.NavigationToUserEvent -> {
                        controller.navigate("userScreen")
                    }
                    is FrontScreenEvent.NavigationToArticleDetailEvent -> {
                        controller.navigate("articleDetailScreen/${event.id}")
                    }
                    FrontScreenEvent.NavigationToNewArticleEvent -> {
                        controller.navigate("newArticleScreen")
                    }
                }

            }
        }
        composable("userScreen") {
            UserScreen()
        }
        composable("newArticleScreen") {
            NewArticleScreen(viewModel = viewModel) { event ->
                when (event) {
                    NewArticleScreenEvent.CancelEvent -> controller.popBackStack()
                    is NewArticleScreenEvent.ConfirmEvent -> {
                        val article = Article(
                            id = defaultId(),
                            avatarUrl = defaultAvatarUrl(event.content),
                            title = defaultTitle(event.content).toString(),
                            shortContent = defaultShortContent(event.content).toString(),
                            content = event.content,
                            date = defaultDate()
                        )
                        viewModel.createNewArticle(article)
                        controller.popBackStack()
                    }

                }


            }
        }


        composable(
            "articleDetailScreen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { entry ->
            entry.arguments?.getInt("id")?.let { id ->
                ArticleDetailScreen(articleId = id) { event ->
                    when (event) {
                        ArticleDetailEvent.BackToFront -> controller.popBackStack()
                    }

                }
            }
        }

    }


}
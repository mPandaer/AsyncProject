package space.pandaer.asyncproject

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import space.pandaer.asyncproject.frontmoudel.model.Article
import space.pandaer.asyncproject.frontmoudel.remote.ArticleRemoteSource
import space.pandaer.asyncproject.frontmoudel.ui.FrontScreen
import space.pandaer.asyncproject.navigationmoudel.MainNavigation
import space.pandaer.asyncproject.ui.theme.AsyncProjectTheme
import space.pandaer.asyncproject.usermoudel.model.User
import space.pandaer.asyncproject.usermoudel.remote.UserRemoteSource

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsyncProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AsyncProjectTheme {
        Greeting("Android")
    }
}
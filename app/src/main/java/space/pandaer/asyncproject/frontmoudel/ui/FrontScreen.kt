package space.pandaer.asyncproject.frontmoudel.ui

import android.annotation.SuppressLint
import android.os.Build
import android.provider.ContactsContract
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import space.pandaer.asyncproject.frontmoudel.states.ArticleState
import space.pandaer.asyncproject.util.common.AvatarImage

import space.pandaer.asyncproject.R
import space.pandaer.asyncproject.frontmoudel.ArticleViewModel
import space.pandaer.asyncproject.frontmoudel.model.Article
import space.pandaer.asyncproject.frontmoudel.states.rememberArticleState
import space.pandaer.asyncproject.util.test.testArticle
import space.pandaer.asyncproject.util.test.testArticleList
import androidx.lifecycle.viewmodel.compose.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import space.pandaer.asyncproject.basenetwork.DataState
import space.pandaer.asyncproject.util.common.FailureScreen
import space.pandaer.asyncproject.util.common.IconItem
import space.pandaer.asyncproject.util.common.LoadingScreen


sealed interface FrontScreenEvent {
    object NavigationToUserEvent : FrontScreenEvent
    object NavigationToNewArticleEvent : FrontScreenEvent
    class NavigationToArticleDetailEvent(val id: Int) : FrontScreenEvent
}

@Preview
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FrontScreen(
    viewModel: ArticleViewModel = viewModel(),
    onEvent: (FrontScreenEvent) -> Unit = {}
) {
    val state: DataState by viewModel.allArticleState.collectAsState()
    val createState by viewModel.createArticleState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val (visibilitySnackBar, setSnackBar) = remember {
        mutableStateOf(false)
    }
    if (createState is DataState.Failure || createState is DataState.Success<*>) setSnackBar(true) else setSnackBar(
        false
    )
    Scaffold(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        scaffoldState = scaffoldState,
        topBar = {
            DashBroad {
                onEvent(FrontScreenEvent.NavigationToUserEvent)
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(FrontScreenEvent.NavigationToNewArticleEvent) }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "add new article")
            }
        }
    ) {


        when (state) {
            DataState.Waiting -> {
                LaunchedEffect(key1 = state) {
                    viewModel.getAllArticles()
                }
            }
            DataState.Loading -> {
                LoadingScreen()

            }
            is DataState.Failure -> {
                val msg = (state as DataState.Failure).msg
                FailureScreen(msg) {
                    viewModel.reLoadingInList()
                }
            }
            is DataState.Success<*> -> {
                if ((state as DataState.Success<*>).data is List<*>) {
                    val list = (state as DataState.Success<List<Article>>).data
                    ArticleList(
                        Modifier.padding(top = it.calculateTopPadding()),
                        list = list
                    ) { id ->
                        onEvent(FrontScreenEvent.NavigationToArticleDetailEvent(id))
                    }
                    LaunchedEffect(visibilitySnackBar) {
                        if (visibilitySnackBar) {
                            Log.d("xixi", "显示sanckbar")
                            val text =
                                if (createState is DataState.Failure) "失败了：${(createState as DataState.Failure).msg}" else "成功了"
                            scaffoldState.snackbarHostState.showSnackbar(message = text)
                            viewModel.reLoadingInCreate() //防止数据错乱
                        }
                    }
                }
            }
        }


    }
}


@Preview
@Composable
fun DashBroad(onClick: () -> Unit = {}) {
    Card(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AvatarImage(
                modifier = Modifier
                    .size(36.dp)
                    .clickable { onClick() }
            )
            Text(
                text = "首页",
                fontSize = 24.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(36.dp))
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    list: List<Article?> = testArticleList,
    onClick: (Int) -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        items(list) { article ->
            article?.let {
                ItemArticle(
                    articleState = rememberArticleState(article = article),
                    modifier = modifier.clickable { onClick(article.id) })
            }
        }
    }
}

@Preview(showBackground = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ItemArticle(
    modifier: Modifier = Modifier,
    articleState: ArticleState = rememberArticleState(article = testArticle)
) {
    Box {
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            UserInfo(articleState)
            ContentText(articleState.shortContent)
            ContentBottomBar()
            Divider(Modifier.padding(vertical = 8.dp), color = Color.LightGray.copy(alpha = 0.2f))
        }
    }

}

@Composable
private fun UserInfo(articleState: ArticleState) {
    Row(modifier = Modifier.fillMaxWidth()) {
        AvatarImage(modifier = Modifier.size(18.dp), avatarUrl = articleState.avatarUrl)
        Column(Modifier.padding(start = 4.dp)) {
            Text(text = "pandaer", fontSize = 12.sp)
            Text(text = articleState.date, fontSize = 10.sp, color = Color.LightGray)
        }
    }
}

@Composable
private fun ContentText(content: String) {
    Text(
        text = content,
        fontSize = 12.sp,
        fontWeight = FontWeight.Light,
        overflow = TextOverflow.Ellipsis,
        maxLines = 9,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    )
}

@Composable
private fun ContentBottomBar() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        IconItem(id = R.drawable.like, des = "点赞") { state ->
            //TODO(“实现根据状态改变点赞数”)
        }
        IconItem(id = R.drawable.comment, des = "评论") {
            //TODO(“实现根据状态改变评论数”)
        }
        IconItem(id = R.drawable.favorite, des = "收藏") {
            //TODO(“实现根据状态改变收藏数”)
        }

    }
}



package space.pandaer.asyncproject.frontmoudel.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import space.pandaer.asyncproject.frontmoudel.states.ArticleState
import space.pandaer.asyncproject.util.common.AvatarImage

import space.pandaer.asyncproject.R
import space.pandaer.asyncproject.frontmoudel.model.Article
import space.pandaer.asyncproject.frontmoudel.states.rememberArticleState
import space.pandaer.asyncproject.util.test.testArticle
import space.pandaer.asyncproject.util.test.testArticleList


sealed interface FrontScreenEvent {
    object NavigationToUserEvent : FrontScreenEvent
    class NavigationToArticleDetailEvent(val id: Int) : FrontScreenEvent
}

@Preview
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FrontScreen(onEvent: (FrontScreenEvent) -> Unit = {}) {
    Scaffold(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp), topBar = {
            DashBroad {
                onEvent(FrontScreenEvent.NavigationToUserEvent)
            }
        }) {
        ArticleList(Modifier.padding(top = it.calculateTopPadding())) {
            onEvent(FrontScreenEvent.NavigationToArticleDetailEvent(1))
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
                text = "Dash",
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
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
    onClick: () -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        items(list) { article ->
            article?.let {
                ItemArticle(
                    articleState = rememberArticleState(article = article),
                    modifier = modifier.clickable { onClick() })
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
        fontSize = 14.sp,
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IconItem(
    modifier: Modifier = Modifier.size(18.dp),
    clicked: Boolean = false,
    id: Int,
    des: String,
    onClick: (Boolean) -> Unit
) {
    var clickedState by remember {
        mutableStateOf(clicked)
    }
    AnimatedContent(targetState = clickedState, transitionSpec = {
        fadeIn(tween(1000)) with fadeOut(
            tween(1000)
        )
    }, modifier = Modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(8.dp))
        .clickable {
            clickedState = !clickedState
            onClick(clickedState)
        }) {
        Box(Modifier.padding(4.dp)) {
            if (clickedState) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = id),
                    contentDescription = "icon",
                    tint = Color.Red,
                    modifier = modifier
                )
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = id),
                        contentDescription = "icon",
                        tint = Color.LightGray,
                        modifier = modifier
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = des, color = Color.LightGray, fontSize = 10.sp)
                }
            }
        }

    }
}

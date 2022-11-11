package space.pandaer.asyncproject.frontmoudel.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import space.pandaer.asyncproject.R
import space.pandaer.asyncproject.basenetwork.DataState
import space.pandaer.asyncproject.frontmoudel.ArticleViewModel
import space.pandaer.asyncproject.frontmoudel.model.Article
import space.pandaer.asyncproject.frontmoudel.states.ArticleState
import space.pandaer.asyncproject.frontmoudel.states.rememberArticleState
import space.pandaer.asyncproject.util.common.AvatarImage
import space.pandaer.asyncproject.util.common.FailureScreen
import space.pandaer.asyncproject.util.common.IconItem
import space.pandaer.asyncproject.util.common.LoadingScreen
import space.pandaer.asyncproject.util.test.testArticle
import androidx.lifecycle.viewmodel.compose.*
import java.lang.Exception


sealed interface ArticleDetailEvent {
    object BackToFront : ArticleDetailEvent
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticleViewModel = viewModel(),
    articleId: Int = 1,
    onEvent: (ArticleDetailEvent) -> Unit = {}
) {

    val state: DataState by viewModel.detailArticleState.collectAsState()
    when (state) {
        DataState.Waiting -> {
            LaunchedEffect(key1 = state) {
                viewModel.getSingleArticle(id = articleId)
            }
        }
        DataState.Loading -> {
            LoadingScreen()
        }
        is DataState.Failure -> {
            val msg = (state as DataState.Failure).msg
            FailureScreen(msg) {
                viewModel.reLoadingInDetail()
            }
        }
        is DataState.Success<*> -> {

                if ((state as DataState.Success<*>).data is Article) {
                    val newArticle = (state as DataState.Success<Article>).data
                    val newArticleState = rememberArticleState(article = newArticle)
                    Column(modifier = modifier.fillMaxSize()) {
                        DetailTopRow(onBack = { onEvent(ArticleDetailEvent.BackToFront) })
                        UserInfoRow()
                        newArticleState.content?.let { DetailBlock(content = it) }
                        DetailBottomRow()
                    }
                }



        }
    }


}

@Preview
@Composable
fun DetailTopRow(modifier: Modifier = Modifier, onBack: () -> Unit = {}) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { onBack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back front",
                modifier = Modifier.size(16.dp)
            )
        }
        Text(text = "详情", modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.size(16.dp))


    }
}

@Preview
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserInfoRow(
    modifier: Modifier = Modifier, articleState: ArticleState = rememberArticleState(
        article = testArticle
    )
) {
    Row(modifier.fillMaxWidth()) {
        AvatarImage(modifier = Modifier.size(14.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Column {
            Text(text = "pandaer", fontSize = 12.sp) //todo(匿名策略还没想好)
            Text(text = articleState.date, fontSize = 8.sp, color = Color.LightGray)
        }
    }


}


@Preview
@Composable
fun DetailBlock(content: String = "") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(text = content, fontSize = 14.sp)
    }

}

@Preview
@Composable
fun DetailBottomRow() {
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

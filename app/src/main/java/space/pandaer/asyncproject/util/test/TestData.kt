package space.pandaer.asyncproject.util.test

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import org.w3c.dom.Text
import space.pandaer.asyncproject.R
import space.pandaer.asyncproject.frontmoudel.model.Article
import space.pandaer.asyncproject.frontmoudel.states.EditTextState
import space.pandaer.asyncproject.frontmoudel.states.rememberArticleState
import space.pandaer.asyncproject.usermoudel.model.User
import space.pandaer.asyncproject.util.common.dateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val defaultAvatarUrl = "https://avatars.dicebear.com/api/adventurer/dddd.svg"

@RequiresApi(Build.VERSION_CODES.O)
val testArticle = Article(
    id = 1,
    avatarUrl = defaultAvatarUrl,
    title = "pandaer的杂货铺",
    shortContent = "爱吃糖的pandaer电脑坏了",
    content = "具体的原因是主板寄了",
    date = dateFormat()
)

@RequiresApi(Build.VERSION_CODES.O)
val testArticleList = listOf(
    testArticle,
    testArticle,
    testArticle,
    testArticle
)

@RequiresApi(Build.VERSION_CODES.O)
val testUser = User(
    account = "1111",
    password = "jkjk",
    createTime = dateFormat(),
    updateTime = dateFormat(),
    email = "pandaer@gmail.com"
)

val testTextState = EditTextState()

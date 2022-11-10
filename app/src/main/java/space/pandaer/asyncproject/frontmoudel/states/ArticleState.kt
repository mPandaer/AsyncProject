package space.pandaer.asyncproject.frontmoudel.states

import androidx.compose.runtime.*
import space.pandaer.asyncproject.frontmoudel.model.Article

class ArticleState(private val article:Article) {
    var content by mutableStateOf(article.content)
    var date by mutableStateOf(article.date)
    var avatarUrl by mutableStateOf(article.avatarUrl)
    var title by mutableStateOf(article.title)
    var id by mutableStateOf(article.id)
    var shortContent by mutableStateOf(article.shortContent)
}

@Composable
fun rememberArticleState(article: Article) = remember {
    ArticleState(article)
}
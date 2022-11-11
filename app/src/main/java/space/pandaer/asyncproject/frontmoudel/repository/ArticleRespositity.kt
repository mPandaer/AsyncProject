package space.pandaer.asyncproject.frontmoudel.repository

import space.pandaer.asyncproject.frontmoudel.model.Article
import space.pandaer.asyncproject.frontmoudel.remote.ArticleRemoteSource


//整合本地数据和远程数据 始终保持单一数据源
object ArticleRepository {
    private val remoteSource = ArticleRemoteSource

    suspend fun getAllArticle() = remoteSource.getAllArticles()
    suspend fun getSingleArticle(id: Int) = remoteSource.getSingleArticle(id)
    suspend fun createNewArticle(newArticle: Article) =
        remoteSource.createNewArticle(newArticle = newArticle)


}
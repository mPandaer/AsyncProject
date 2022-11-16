package space.pandaer.asyncproject.frontmoudel.repository

import android.annotation.SuppressLint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import space.pandaer.asyncproject.basenetwork.DataState
import space.pandaer.asyncproject.frontmoudel.datasource.ArticleLocalSource
import space.pandaer.asyncproject.frontmoudel.model.Article
import space.pandaer.asyncproject.frontmoudel.datasource.ArticleRemoteSource


//整合本地数据和远程数据 始终保持单一数据源
object ArticleRepository {
    private val remoteSource = ArticleRemoteSource

    @SuppressLint("StaticFieldLeak")
    private val localSource = ArticleLocalSource.INSTANCE

    suspend fun getAllArticle(): DataState {
        val list = remoteSource.getAllArticles().articles
            ?: return DataState.Failure("文章列表为空")
        list.filterNotNull().forEach {
           localSource.addArticle(it)
        }
        return DataState.Success(localSource.getAllArticles()) //整合数据源始终保持单一数据源
    }

    suspend fun getSingleArticle(id: Int) : DataState {
        var article = localSource.getSingleArticles(id)
        if (article == null) article = remoteSource.getSingleArticle(id).data
        if (article == null) return DataState.Failure("文章信息为空")
            else localSource.addArticle(article)
        return DataState.Success(localSource.getSingleArticles(id))
    }
    suspend fun createNewArticle(newArticle: Article) =
        remoteSource.createNewArticle(newArticle = newArticle)


}
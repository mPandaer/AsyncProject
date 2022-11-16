package space.pandaer.asyncproject.frontmoudel.datasource

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import space.pandaer.asyncproject.frontmoudel.model.Article
import space.pandaer.asyncproject.util.data.AsyncDataBase

class ArticleLocalSource private constructor(private val context: Context) {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var localRepository: ArticleLocalSource
        fun initLocalSource(context: Context): ArticleLocalSource {
            if (!(Companion::localRepository).isInitialized) {
                localRepository = ArticleLocalSource(context)
            }
            return localRepository
        }

        val INSTANCE
            get() = if ((Companion::localRepository).isInitialized) localRepository else throw ExceptionInInitializerError(
                "本地存储库未初始化"
            )

    }


    private val database =
        Room.databaseBuilder(context, AsyncDataBase::class.java, "async_db").build()
    private val articleDao = database.getArticleDao()
    private val userDao = database.getUserDao()

    //获取全部文章
    suspend fun getAllArticles(): List<Article> = withContext(Dispatchers.IO) {
        articleDao.getAllArticles()
    }

    //获取单篇文章
    suspend fun getSingleArticles(id: Int): Article? = withContext(Dispatchers.IO) {
        articleDao.getSingleArticle(id)
    }

    //增加文章
    suspend fun addArticle(article: Article) = withContext(Dispatchers.IO) {
        articleDao.addArticle(article)
    }

    //更新文章
    suspend fun updateArticle(article: Article) = withContext(Dispatchers.IO) {
        articleDao.updateArticle(article)
    }

    //删除文章
    suspend fun deleteArticle(article: Article) = withContext(Dispatchers.IO) {
        articleDao.deleteArticle(article)
    }


}
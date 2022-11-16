package space.pandaer.asyncproject.frontmoudel.datasource

import retrofit2.converter.gson.GsonConverterFactory
import space.pandaer.asyncproject.basenetwork.ServiceFactory
import space.pandaer.asyncproject.frontmoudel.model.Article

object ArticleRemoteSource {
    private val articleService = ServiceFactory.createService(ArticleApi::class.java) {
        //retrofit 配置文件
        baseUrl("http://10.0.2.2:4523/m1/1854925-0-default/")
        addConverterFactory(GsonConverterFactory.create())
    }

    //获取全部文章
    suspend fun getAllArticles() = articleService.getAllArticles()

    //获取单篇文章
    suspend fun getSingleArticle(id: Int) = articleService.getSingleArticle(id)

    //创建一篇文章
    suspend fun createNewArticle(newArticle: Article) =
        articleService.createNewArticle(newArticle)


}
package space.pandaer.asyncproject.frontmoudel.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import space.pandaer.asyncproject.frontmoudel.model.Article
import space.pandaer.asyncproject.frontmoudel.model.CreateStatus
import space.pandaer.asyncproject.frontmoudel.model.WrappedArticle
import space.pandaer.asyncproject.frontmoudel.model.WrappedArticleList


//请求文章的api
interface ArticleApi {

    //获取全部文章
    @GET("article/all")
    suspend fun getAllArticles() : WrappedArticleList

    //获取单篇文章
    @GET("article/{id}")
    suspend fun getSingleArticle(@Path("id") id : Int) : WrappedArticle

    //创建新文章
    @POST("article/create")
    suspend fun createNewArticle(@Body newArticle : Article) : CreateStatus

}
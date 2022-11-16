package space.pandaer.asyncproject.frontmoudel.model

import androidx.room.*

@Dao
interface ArticleDao {
    //添加文章
    //添加的文章冲突就替换
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArticle(article: Article)

    //更新文章
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateArticle(article: Article)

    //删除文章
    @Delete
    fun deleteArticle(article: Article)

    //查询全部文章
    @Query("SELECT * FROM article")
    fun getAllArticles() : List<Article>

    //查询单篇文章
    @Query("SELECT * FROM article WHERE id = :id")
    fun getSingleArticle(id :Int) : Article?
}
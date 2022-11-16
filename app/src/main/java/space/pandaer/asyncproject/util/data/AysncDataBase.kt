package space.pandaer.asyncproject.util.data

import androidx.room.Database
import androidx.room.RoomDatabase
import space.pandaer.asyncproject.frontmoudel.model.Article
import space.pandaer.asyncproject.frontmoudel.model.ArticleDao
import space.pandaer.asyncproject.usermoudel.model.User
import space.pandaer.asyncproject.usermoudel.model.UserDao


@Database(entities = [Article::class,User::class], version = 1)
abstract class AsyncDataBase : RoomDatabase() {
    abstract fun getArticleDao() :ArticleDao
    abstract fun getUserDao() : UserDao
}
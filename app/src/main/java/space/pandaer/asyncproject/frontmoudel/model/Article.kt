package space.pandaer.asyncproject.frontmoudel.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import space.pandaer.asyncproject.util.common.dateFormat
import java.time.LocalDateTime
import java.util.*


//文章数据
@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("avatar")
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    val title: String,
    @SerializedName("shortcount")
    @ColumnInfo(name = "short_content")
    val shortContent: String,
    val content: String?,
    val date: String
)

//包装文章信息 主要用于http的报文序列化
data class WrappedArticle(val status: String, val data: Article?)


@RequiresApi(Build.VERSION_CODES.O)
fun defaultId() = LocalDateTime.now().hashCode()

fun defaultAvatarUrl(content:String) = "https://avatars.dicebear.com/api/adventurer/${content}.svg"

@RequiresApi(Build.VERSION_CODES.O)
fun defaultDate() = dateFormat()

fun defaultShortContent(content:String?) = content?.subSequence(0 until minOf(content.length, 18)) ?: "标题未命名"
fun defaultTitle(content:String?) = content?.subSequence(0 until minOf(content.length, 5)) ?: "标题未命名"
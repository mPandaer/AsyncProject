package space.pandaer.asyncproject.frontmoudel.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import space.pandaer.asyncproject.util.common.dateFormat
import java.time.LocalDateTime
import java.util.*


//文章数据
data class Article(
    val id: Int,
    @SerializedName("avatar")
    val avatarUrl: String,
    val title: String,
    @SerializedName("shortcount")
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
package space.pandaer.asyncproject.frontmoudel.model

import com.google.gson.annotations.SerializedName


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
data class WrappedArticle(val status:String,val data:Article?)
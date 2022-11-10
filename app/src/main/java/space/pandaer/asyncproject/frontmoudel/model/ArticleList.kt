package space.pandaer.asyncproject.frontmoudel.model

import com.google.gson.annotations.SerializedName

//主要为了方便序列化http报文
data class WrappedArticleList(
    val status:Int, //获取文章的状态
    val message:String, //失败的信息
    val date:String, //时间
    @SerializedName("data")
    val articles:List<Article?>? //文章列表
)
package space.pandaer.asyncproject.usermoudel.model

import com.google.gson.annotations.SerializedName

data class User(
    val account: String,
    val password: String,
    @SerializedName("create_time")
    val createTime: String,
    @SerializedName("update_time")
    val updateTime: String,
    val email: String
)
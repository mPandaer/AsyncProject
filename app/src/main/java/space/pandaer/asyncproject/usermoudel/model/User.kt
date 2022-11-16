package space.pandaer.asyncproject.usermoudel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val account: String,
    val password: String,
    @SerializedName("create_time")
    @ColumnInfo(name = "create_time")
    val createTime: String,
    @SerializedName("update_time")
    @ColumnInfo(name = "update_time")
    val updateTime: String,
    val email: String
)
package space.pandaer.asyncproject.usermoudel.remote

import okhttp3.FormBody
import retrofit2.http.Body
import retrofit2.http.POST
import space.pandaer.asyncproject.usermoudel.model.LoginState

interface UserApi {
    //用户登录
    @POST("user/login")
    suspend fun login(@Body body:FormBody) : LoginState
}
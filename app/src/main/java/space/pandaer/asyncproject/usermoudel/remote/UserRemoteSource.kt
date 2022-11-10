package space.pandaer.asyncproject.usermoudel.remote

import okhttp3.FormBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.pandaer.asyncproject.basenetwork.ServiceFactory
import space.pandaer.asyncproject.usermoudel.model.LoginState
import space.pandaer.asyncproject.usermoudel.model.User

object UserRemoteSource {
    private val userService = ServiceFactory.createService(UserApi::class.java){
        baseUrl("http://10.0.2.2:4523/m1/1854925-0-default/")
        addConverterFactory(GsonConverterFactory.create())
    }

    suspend fun login(user:User) : LoginState {
        val body = FormBody.Builder()
            .add("account",user.account)
            .add("password",user.password)
            .build()

        return userService.login(body)
    }

}
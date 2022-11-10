package space.pandaer.asyncproject.usermoudel.states

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import space.pandaer.asyncproject.usermoudel.model.User
import space.pandaer.asyncproject.util.test.testUser

class UserState(private val user:User) {
    val email by mutableStateOf(user.email)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun rememberUserState(user: User = testUser) = remember {
    UserState(user = user)
}
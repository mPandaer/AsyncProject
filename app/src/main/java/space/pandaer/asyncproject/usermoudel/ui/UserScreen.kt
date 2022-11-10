package space.pandaer.asyncproject.usermoudel.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import space.pandaer.asyncproject.usermoudel.states.UserState
import space.pandaer.asyncproject.usermoudel.states.rememberUserState
import space.pandaer.asyncproject.util.common.AvatarImage


import space.pandaer.asyncproject.R

@Preview(showBackground = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserScreen(userState: UserState = rememberUserState()) {
    Column(Modifier.padding(8.dp)) {
        UserBroad(email = userState.email)
        RowItem(resId = R.drawable.favorite, des = "喜爱")
        RowItem(resId = R.drawable.favorite, des = "喜爱")
        RowItem(resId = R.drawable.favorite, des = "喜爱")
    }

}

@Composable
fun UserBroad(modifier: Modifier = Modifier, email: String) {
    Column(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            AvatarImage(modifier = Modifier.size(24.dp))
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(text = "pandaer", fontSize = 14.sp)
                Text(text = email, fontSize = 10.sp, color = Color.LightGray)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)) {
            SingleItem()
            SingleItem()
            SingleItem()
        }
        Divider(color = Color.LightGray.copy(0.2f))
        Spacer(modifier = Modifier.height(12.dp))


    }
}

@Composable
fun RowItem(modifier: Modifier = Modifier,resId: Int, des: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp),) {
        Row(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = resId),
                contentDescription = des,
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(text = des, fontSize = 14.sp)
        }
    }

}

@Composable
fun SingleItem(modifier: Modifier = Modifier,num: String = "99", des: String = "点赞") {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = num, fontSize = 12.sp)
        Text(text = des, fontSize = 10.sp, color = Color.LightGray)
    }
}


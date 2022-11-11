package space.pandaer.asyncproject.util.common

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import space.pandaer.asyncproject.util.test.defaultAvatarUrl

import space.pandaer.asyncproject.R

@Preview
@Composable
fun AvatarImage(modifier: Modifier = Modifier, avatarUrl: String = defaultAvatarUrl) {
    Box(
        modifier = Modifier
            .padding(all = 4.dp)
            .clip(CircleShape)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatarUrl)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            placeholder = painterResource(id = R.drawable.loading),
            contentDescription = "user image",
            modifier = modifier.align(Alignment.Center),
            contentScale = ContentScale.Fit,
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IconItem(
    modifier: Modifier = Modifier.size(18.dp),
    clicked: Boolean = false,
    id: Int,
    des: String,
    onClick: (Boolean) -> Unit
) {
    var clickedState by remember {
        mutableStateOf(clicked)
    }
    AnimatedContent(targetState = clickedState, transitionSpec = {
        fadeIn(tween(1000)) with fadeOut(
            tween(1000)
        )
    }, modifier = Modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(8.dp))
        .clickable {
            clickedState = !clickedState
            onClick(clickedState)
        }) {
        Box(Modifier.padding(4.dp)) {
            if (clickedState) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = id),
                    contentDescription = "icon",
                    tint = Color.Red,
                    modifier = modifier
                )
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = id),
                        contentDescription = "icon",
                        tint = Color.LightGray,
                        modifier = modifier
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = des, color = Color.LightGray, fontSize = 10.sp)
                }
            }
        }

    }
}


@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 18.dp)
        )
    }
}


@Composable
fun FailureScreen(failureMsg:String,onClick: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "网络故障：${failureMsg}", modifier = Modifier.padding(bottom = 4.dp), fontSize = 14.sp)
        TextButton(onClick = { onClick() }) {
            Text(text = "点击重试", color = Color.Red)
        }
    }
}
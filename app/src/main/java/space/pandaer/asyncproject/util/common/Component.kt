package space.pandaer.asyncproject.util.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import space.pandaer.asyncproject.util.test.defaultAvatarUrl

import space.pandaer.asyncproject.R

@Preview
@Composable
fun AvatarImage(modifier: Modifier = Modifier,avatarUrl : String = defaultAvatarUrl) {
    Box(modifier = Modifier
        .padding(all = 4.dp)
        .clip(CircleShape)){
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
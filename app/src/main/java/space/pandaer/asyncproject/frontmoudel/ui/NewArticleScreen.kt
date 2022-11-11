package space.pandaer.asyncproject.frontmoudel.ui

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import space.pandaer.asyncproject.frontmoudel.ArticleViewModel
import space.pandaer.asyncproject.frontmoudel.states.EditTextState
import space.pandaer.asyncproject.frontmoudel.states.NewContentState
import space.pandaer.asyncproject.frontmoudel.states.rememberEditTextState
import androidx.lifecycle.viewmodel.compose.*
import kotlinx.coroutines.launch
import space.pandaer.asyncproject.basenetwork.DataState
import space.pandaer.asyncproject.frontmoudel.model.CreateStatus
import space.pandaer.asyncproject.util.common.FailureScreen
import space.pandaer.asyncproject.util.common.LoadingScreen

sealed interface NewArticleScreenEvent {
    object CancelEvent : NewArticleScreenEvent
    class ConfirmEvent(val content: String) : NewArticleScreenEvent
}


@SuppressLint("CoroutineCreationDuringComposition")
@Preview(showBackground = true)
@Composable
fun NewArticleScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticleViewModel = viewModel(),
    onEvent: (NewArticleScreenEvent) -> Unit = {}
) {
    val dataState by viewModel.createArticleState.collectAsState()

    Column(modifier.fillMaxSize()) {
        val contentState = NewContentState()
        NewTopBar(
            onCancel = { onEvent(NewArticleScreenEvent.CancelEvent) },
            onConfirm = { onEvent(NewArticleScreenEvent.ConfirmEvent(contentState.value)) })
        Spacer(modifier = Modifier.height(12.dp))
        EditContent(modifier = Modifier.weight(1f), textState = contentState)


    }
}

@Preview(showBackground = true)
@Composable
fun NewTopBar(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(onClick = { onCancel() }) {
            Text(text = "取消")
        }
        Text(text = "吐槽一下")
        TextButton(onClick = { onConfirm() }) {
            Text(text = "确定")
        }
    }
}

@Composable
fun EditContent(modifier: Modifier = Modifier, textState: EditTextState = rememberEditTextState()) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        TextField(
            value = textState.value,
            onValueChange = textState::onTextChange,
            modifier = Modifier.fillMaxSize(),
            placeholder = {
                Text(text = "吐槽一下吧...", color = Color.LightGray, fontSize = 12.sp)
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}

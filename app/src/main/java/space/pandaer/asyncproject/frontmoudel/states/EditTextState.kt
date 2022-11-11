package space.pandaer.asyncproject.frontmoudel.states

import androidx.compose.runtime.*
import space.pandaer.asyncproject.util.test.testTextState

open class EditTextState {
    var value : String by mutableStateOf("")
    fun onTextChange(newText:String) {
        value = newText
    }
}

@Composable
fun rememberEditTextState() =
    remember {
        testTextState
    }

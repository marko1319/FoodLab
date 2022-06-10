package com.example.foodlab.core.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodlab.core.theme.AppTypography
import com.example.foodlab.core.theme.ColorBackground

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    label: String = "",
    maxLines: Int=1,
    action: ImeAction = ImeAction.Done,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}

) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = ColorBackground
        ),
        label = { MyText(
            text = label,
            style = AppTypography.small.light,
            color = Color.Black
        )
        },maxLines=maxLines,
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = action),
        keyboardActions = KeyboardActions (onDone = {
            onImeAction()
            keyboardController?.hide()
        })
    )
}


@Preview
@Composable
fun MyTextFieldPreview(){
    MyTextField(text="Ime", label = "Ime", onTextChange = {})
}



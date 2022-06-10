package com.example.foodlab.core.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodlab.core.theme.AppTypography

@Composable
fun MyText(
    modifier: Modifier = Modifier,
    text: String ="Placeholder",
    color: Color = Color.White,
    style: TextStyle = AppTypography.normal.medium,
    textAlign: TextAlign = TextAlign.Center,

    ) {

    Text(
        textAlign=textAlign,
        text = text,
        modifier = modifier,
        color = color,
        style = style
    )
}

@Preview
@Composable
fun MyTextPreview(){
    MyText(text = "Poručite klopu")
}
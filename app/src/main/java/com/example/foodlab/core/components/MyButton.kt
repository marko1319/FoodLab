package com.example.foodlab.core.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodlab.R
import com.example.foodlab.core.theme.AppTypography
import com.example.foodlab.core.theme.ColorBackground
import com.example.foodlab.core.theme.ColorDugme
import com.example.foodlab.core.theme.ColorPocetnoDugme

@Composable
fun MyButton(

    modifier: Modifier = Modifier,
    icon: Int? = null,
    text: String ="Placeholder",
    color: Color = ColorDugme,
    enabled: Boolean = true,
    onClick: () -> Unit = {}){

    Button(
        modifier = modifier,
        colors= ButtonDefaults.buttonColors(backgroundColor = color, contentColor = ColorBackground, disabledBackgroundColor = ColorPocetnoDugme),
        shape = RoundedCornerShape(50.dp),
        enabled = enabled,
        onClick = {
            onClick()
        }) {
        Row() {
            MyText(
                modifier = Modifier.padding(20.dp),
                text = text, style = AppTypography.normal.bold,
                color = ColorBackground )
            if(icon != null){
                Icon(painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier.padding(start = 15.dp).width(20.dp).height(20.dp))
            }
        }

    }
}
//
@Preview
@Composable
fun MyButtonPreview(){
    MyButton()
}
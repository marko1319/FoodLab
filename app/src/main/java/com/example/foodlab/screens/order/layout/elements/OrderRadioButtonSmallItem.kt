package com.example.foodlab.screens.order.layout.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.foodlab.core.components.MyText
import com.example.foodlab.core.theme.ColorDugme

@Composable
fun OrderRadioButtonSmallItem(
    selected: Boolean,
    text: String?,
    onClick: (String) -> Unit = {}
) {

    var selectedMutable  by remember{ mutableStateOf(selected) }

    ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp,16.dp)
                .clickable ( onClick = {
                    selectedMutable = !selectedMutable
                    onClick(text?:"")

                } )

        ) {
            val (radioRef, textRef) = createRefs()

            MyText(
                modifier = Modifier.constrainAs(textRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
                color = Color.Black,
                text = text?:""
            )

            RadioButton(
                selected = (selectedMutable),
                modifier = Modifier.constrainAs(radioRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = ColorDugme,
                    unselectedColor = ColorDugme
                ),
                onClick = null)


        }
}

@Preview
@Composable
fun PreviewOrderRadioButtonSmallItem() {
    OrderRadioButtonSmallItem(selected = false, text = "Pirinac")
}
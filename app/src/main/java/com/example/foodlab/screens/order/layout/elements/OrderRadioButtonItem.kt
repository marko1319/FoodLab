package com.example.foodlab.screens.order.layout.elements

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
fun OrderRadioButtonItem(
    selected: Boolean,
    text: String?,
    price: String,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable(onClick = {
                onClick(text?:"")
            }),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = if (selected)  Color.White else ColorDugme
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            val (radioRef, textRef, priceRef) = createRefs()

            RadioButton(
                selected = (selected),
                modifier = Modifier.constrainAs(radioRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Black,
                    unselectedColor = Color.White
                ),
                onClick = null )
            MyText(
                modifier = Modifier.constrainAs(textRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(radioRef.end, margin = 8.dp)
                },
                color = if (selected) Color.Black else Color.White,
                text = text?:""
            )
            MyText(
                modifier = Modifier.constrainAs(priceRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
                color = if (selected) Color.Gray else Color.White,
                text = "$price RSD"
            )
        }
    }

}

@Preview
@Composable
fun PreviewOrderRadioButtonItem() {
    OrderRadioButtonItem(selected = false, text = "Mala", price = "280")
}
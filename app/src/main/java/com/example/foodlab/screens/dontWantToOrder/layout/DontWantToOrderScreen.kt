package com.example.foodlab.screens.dontWantToOrder.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.foodlab.core.components.MyText
import com.example.foodlab.core.theme.AppTypography
import com.example.foodlab.core.theme.ColorBackground
import com.example.foodlab.core.theme.ColorDugme
import com.example.foodlab.core.utils.ViewModelEvents
import com.example.foodlab.screens.dontWantToOrder.viewModel.DontWantToOrderEvents
import com.example.foodlab.screens.dontWantToOrder.viewModel.DontWantToOrderState
import com.example.foodlab.screens.menu.viewModel.MenuEvents
import com.example.foodlab.screens.menu.viewModel.MenuState
import com.example.foodlab.R
import com.example.foodlab.core.components.MyButton

@Composable
fun DontWantToOrderScreen(state: MutableState<DontWantToOrderState> = mutableStateOf(DontWantToOrderState()),
                          events: ViewModelEvents<DontWantToOrderEvents> = ViewModelEvents()
){
    ConstraintLayout(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(color = ColorBackground)
        .padding(30.dp)
    ){
        val(dovidjenja, tekst, sad_face, dugme, tekst2)=createRefs()

        MyText(modifier = Modifier
            .constrainAs(dovidjenja) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
            ,text = "Dovidjenja, ${state.value.username}", style = AppTypography.Big.bold, color = Color.Black)

        MyText(modifier = Modifier
            .constrainAs(tekst) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            ,text = "Vidimo se sutra!", style = AppTypography.Big.extraSize, color = ColorDugme )

        Image(modifier = Modifier
            .constrainAs(sad_face) {
                bottom.linkTo(tekst.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.padding(10.dp)
            ,painter = painterResource(id = R.mipmap.sad_face), contentDescription ="",
            contentScale = ContentScale.FillWidth)

        MyText(modifier = Modifier
            .constrainAs(tekst2) {
                bottom.linkTo(dugme.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            ,text = "Predomislili ste se?", style = AppTypography.Big.bold, color = Color.Black )

        MyButton(
            modifier = Modifier
                .constrainAs(dugme) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(60.dp)
                .width(250.dp),
            color= ColorDugme,
            text="Nazad na meni",
            onClick = {
                events.sendEvent(DontWantToOrderEvents.Confirm)
            })
    }

}
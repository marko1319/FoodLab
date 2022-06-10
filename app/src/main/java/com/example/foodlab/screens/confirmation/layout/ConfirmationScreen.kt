package com.example.foodlab.screens.confirmation.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.example.foodlab.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.foodlab.core.components.MyButton
import com.example.foodlab.core.components.MyText
import com.example.foodlab.core.theme.AppTypography
import com.example.foodlab.core.theme.ColorBackground
import com.example.foodlab.core.theme.ColorDugme
import com.example.foodlab.core.utils.ViewModelEvents
import com.example.foodlab.screens.confirmation.viewModel.ConfirmationEvents
import com.example.foodlab.screens.confirmation.viewModel.ConfirmationState

@Composable
fun ConfirmationScreen(state: MutableState<ConfirmationState> = mutableStateOf(ConfirmationState()),
                       events: ViewModelEvents<ConfirmationEvents> = ViewModelEvents()) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = ColorBackground)
            .padding(30.dp)
    ) {
        val(tekst, confirm, dugme)=createRefs()

        MyText(modifier = Modifier
            .constrainAs(tekst) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.padding(bottom = 250.dp)
            ,text = "Vaša porudžbina je\n uspešno kreirana", style = AppTypography.Big.extraSize, color = ColorDugme )

        Image(modifier = Modifier
            .constrainAs(confirm) {
                top.linkTo(tekst.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .padding(top = 100.dp)
            ,painter = painterResource(id = R.mipmap.confirm), contentDescription ="",
            contentScale = ContentScale.FillWidth)

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
            text="Nova porudžbina",
            onClick = {
                //navController.popBackStack(route = ApplicationScreens.MenuScreen2.name, inclusive = false)
                events.sendEvent(ConfirmationEvents.Confirm)
            })
    }
}
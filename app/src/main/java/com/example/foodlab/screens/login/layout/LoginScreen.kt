package com.example.foodlab.screens.login.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.foodlab.core.components.MyButton
import com.example.foodlab.core.components.MyText
import com.example.foodlab.core.components.MyTextField
import com.example.foodlab.core.theme.AppTypography
import com.example.foodlab.core.theme.ColorBackground
import com.example.foodlab.core.theme.ColorDugme
import com.example.foodlab.core.utils.ViewModelEvents
import com.example.foodlab.screens.login.viewModel.LoginEvents
import com.example.foodlab.screens.login.viewModel.LoginState
import com.example.foodlab.screens.order.viewModel.OrderEvents

@Composable
@Preview
fun LoginScreen(
    state: MutableState<LoginState> = mutableStateOf(LoginState()),
    events: ViewModelEvents<LoginEvents> = ViewModelEvents()
){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = ColorBackground)
            .padding(30.dp)
    )
    {

        val (tekst, line, logo, ime, prezime, dugme, loadingIndicator) = createRefs()

        MyText(modifier = Modifier
            .constrainAs(tekst) {}
            .padding(horizontal = 50.dp)
            .padding(top = 200.dp),
            text = "Prijavite se",
            style = AppTypography.Big.bold,
            color = Color.Black)

        Image(modifier = Modifier.constrainAs(line) {
            top.linkTo(tekst.bottom)
            start.linkTo(tekst.start)
            end.linkTo(tekst.end)
        },
            painter = painterResource(id = com.example.foodlab.R.mipmap.logline),
            contentDescription = "",
            contentScale = ContentScale.FillWidth)

        Image(modifier = Modifier.constrainAs(logo) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        },
            painter = painterResource(id = com.example.foodlab.R.mipmap.mainlogo),
            contentDescription = "",
            contentScale = ContentScale.FillWidth)

        MyTextField(modifier = Modifier
            .constrainAs(ime) {
                top.linkTo(line.bottom)
                start.linkTo(line.start)
            }
            .padding(vertical = 15.dp),

            text = state.value.firstName?:"",
            label = "Ime",
            action = ImeAction.Next,
            onTextChange = {events.sendEvent(LoginEvents.FirstNameUpdated(it))}
        )



        MyTextField(modifier = Modifier
            .constrainAs(prezime) {
                top.linkTo(ime.bottom)
                start.linkTo(ime.start)
            }
            .padding(vertical = 15.dp),
            label = "Prezime",
            text = state.value.lastName?:"",
            onTextChange = {events.sendEvent(LoginEvents.LastNameUpdated(it))}
        )




        MyButton(
            modifier = Modifier
                .constrainAs(dugme) {
                    top.linkTo(prezime.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth(),
            color = ColorDugme,
            text = "Prijavi se ",
            onClick = {
                events.sendEvent(LoginEvents.LogIn)

            })


        //STATE!!
        if (state.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.constrainAs(loadingIndicator) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        }

//        //STATE!!!
//        if (state.value.isLoading) {
////            navController.navigate(route = ApplicationScreens.MenuScreen2.name)
//        }


    }
}
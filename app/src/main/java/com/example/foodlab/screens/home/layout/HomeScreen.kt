package com.example.foodlab.screens.home.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.foodlab.core.components.MyButton
import com.example.foodlab.core.theme.ColorDugme
import com.example.foodlab.core.theme.ColorPocetnoDugme
import com.example.foodlab.screens.home.viewModel.HomeEvents
import com.example.foodlab.screens.home.viewModel.HomeState
import com.example.foodlab.core.utils.ViewModelEvents
import com.example.foodlab.screens.login.viewModel.LoginEvents

@Composable
fun HomeScreen(
    state: MutableState<HomeState> = mutableStateOf(HomeState()),
    events: ViewModelEvents<HomeEvents> = ViewModelEvents()
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        ConstraintLayout(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth())
        {
            val(logo)=createRefs()
            val(dugme)=createRefs()



            Image(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                ,painter = painterResource(id = com.example.foodlab.R.mipmap.start_background), contentDescription ="",
                contentScale = ContentScale.FillWidth)

            Image(
                modifier = Modifier
                    .constrainAs(logo) {
                        top.linkTo(parent.top)
                    }
                    .padding(50.dp),
                painter = painterResource(id = com.example.foodlab.R.mipmap.logo),
                contentDescription = ""
            )


            MyButton(
                modifier = Modifier
                    .constrainAs(dugme) {
                        top.linkTo(logo.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.width(250.dp)
                    .fillMaxWidth(),
                color = ColorDugme,
                text = "Porucite klopu ",
                onClick = {
                    events.sendEvent(HomeEvents.GoToLogin)

                })
        }
    }

}
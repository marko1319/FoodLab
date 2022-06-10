package com.example.foodlab.screens.profile.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.foodlab.core.components.MyText
import com.example.foodlab.core.theme.AppTypography
import com.example.foodlab.core.theme.ColorBackground
import com.example.foodlab.core.theme.ColorComponents
import com.example.foodlab.core.utils.ViewModelEvents
import com.example.foodlab.screens.profile.viewModel.ProfileEvents
import com.example.foodlab.screens.profile.viewModel.ProfileState
import com.example.foodlab.R
import com.example.foodlab.core.theme.ColorDugme

@Composable
fun ProfileScreen (state: MutableState<ProfileState> = mutableStateOf(ProfileState()),
             events: ViewModelEvents<ProfileEvents> = ViewModelEvents()){
    ConstraintLayout(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(color = ColorBackground)
        .padding(30.dp)){
        val(tekst, meni, avatar, omiljeno, zdravo, krug, stars, istorija)=createRefs()

        MyText(modifier = Modifier
            .constrainAs(zdravo) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
            ,text = "Zdravo, ${state.value.username}", style = AppTypography.Big.bold, color = Color.Black)

        Card(modifier = Modifier
            .size(150.dp)
            .constrainAs(krug) {
                top.linkTo(stars.bottom)
                start.linkTo(stars.start)
                end.linkTo(stars.end)
            }
            .padding(top = 5.dp), shape = CircleShape,
            border = BorderStroke(0.5.dp, Color.LightGray),
            backgroundColor = ColorComponents) {

            val mealName = state.value.listFavoriteMeal.forEach() {
                    it.mealName
                }
            MyText(modifier = Modifier.padding(top = 58.dp),
                text = "$mealName",
                color = ColorDugme, style = AppTypography.Big.medium)
        }


        Image(modifier = Modifier
            .constrainAs(stars) {
                top.linkTo(zdravo.bottom)
                start.linkTo(zdravo.start)
            }
            .padding(top = 20.dp)
            ,painter = painterResource(id = R.mipmap.stars), contentDescription ="",
            contentScale = ContentScale.FillWidth)

        MyText(modifier = Modifier
            .constrainAs(omiljeno) {
                start.linkTo(krug.end)
                top.linkTo(krug.top)
                bottom.linkTo(krug.bottom)
                end.linkTo(parent.end)
            }
            .padding(start = 20.dp)
            ,text = "Tvoje \nomiljeno \njelo ovog \nmeseca ",textAlign = TextAlign.Start, style = AppTypography.Big.extraBold, color = ColorDugme)

        Image(modifier = Modifier
            .constrainAs(avatar) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
            .padding(bottom = 4.dp)
            .clickable {}
            ,painter = painterResource(id = R.mipmap.avatar_dugme), contentDescription ="",
            contentScale = ContentScale.FillWidth)

        Image(modifier = Modifier
            .constrainAs(meni) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
            .padding(top = 26.dp)
            .clickable {
                events.sendEvent(ProfileEvents.menuButtonClicked)
            }
            ,painter = painterResource(id = R.mipmap.meni_dugme), contentDescription ="",
            contentScale = ContentScale.FillWidth)

        MyText(modifier = Modifier
            .constrainAs(tekst) {
                top.linkTo(krug.bottom)
                start.linkTo(krug.start)
            }
            .padding(top = 40.dp)
            .width(300.dp)
            ,text = "Prethodnih dana birala si:", textAlign = TextAlign.Start, style = AppTypography.normal.bold, color = ColorDugme)

        Istorija(modifier = Modifier
            .constrainAs(istorija) {
                top.linkTo(tekst.bottom)
            }
            .height(470.dp))
    }
}

@Composable
fun Istorija(modifier: Modifier, listaPorudzbina: List<String> = listOf("prvi","drugi","treci","cetvrti","peti")){

    LazyColumn(modifier = modifier){
        items(items = listaPorudzbina) {
            Card(
                modifier = Modifier
                    .width(400.dp)
                    .height(117.dp)
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(corner = CornerSize(20.dp)),
                backgroundColor = ColorComponents
            ) {
                //jela iz apija
            }
        }
    }
}



package com.example.foodlab.screens.menu.layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.foodlab.R
import com.example.foodlab.core.components.MyButton
import com.example.foodlab.core.components.MyText
import com.example.foodlab.core.components.MyTextField
import com.example.foodlab.core.theme.*
import com.example.foodlab.core.utils.ViewModelEvents
import com.example.foodlab.screens.menu.viewModel.MenuEvents
import com.example.foodlab.screens.menu.viewModel.MenuState


@Composable
fun MenuScreen(
    state: MutableState<MenuState> = mutableStateOf(MenuState()),
    events: ViewModelEvents<MenuEvents> = ViewModelEvents()
) {

    if(state.value.listMenu.isEmpty()){
        //delay(2000)
        ConstraintLayout(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = ColorBackground)
            .padding(30.dp)){

            val(meni, avatar, tekst, sad_face, zdravo, card)=createRefs()

            MyText(modifier = Modifier
                .constrainAs(zdravo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }.fillMaxWidth()
                ,text = "Zdravo, ${state.value.username}", textAlign = TextAlign.Start, style = AppTypography.Big.bold, color = Color.Black)

            dontWantToOrder(modifier = Modifier.constrainAs(card){
                top.linkTo(zdravo.bottom)
                start.linkTo(zdravo.start)
            },
                onClickItem = { events.sendEvent(MenuEvents.dontWantToOrderClicked) }
            )

            Image(modifier = Modifier
                .constrainAs(meni) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .clickable {}
                ,painter = painterResource(id = R.mipmap.meni_dugme), contentDescription ="",
                contentScale = ContentScale.FillWidth)


            Image(modifier = Modifier
                .constrainAs(avatar) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .padding(bottom = 4.dp)
                .clickable {
                    events.sendEvent(MenuEvents.profileAvatarClicked)
                }
                ,painter = painterResource(id = R.mipmap.avatar_dugme), contentDescription ="",
                contentScale = ContentScale.FillWidth)

            MyText(modifier = Modifier
                .constrainAs(tekst) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                ,text = "Današnji meni nije \n učitan!", textAlign = TextAlign.Center, style = AppTypography.Big.extraSize, color = ColorDugme )

            Image(modifier = Modifier
                .constrainAs(sad_face) {
                    bottom.linkTo(tekst.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(10.dp)
                ,painter = painterResource(id = R.mipmap.sad_face), contentDescription ="",
                contentScale = ContentScale.FillWidth)
        }
    }else{
        ConstraintLayout(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = ColorBackground)
            .padding(30.dp))
        {

            val(meni, avatar, dontOrder, search, jelodana, zdravo, jelo)=createRefs()


            MyText(modifier = Modifier
                .constrainAs(zdravo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                ,text = "Zdravo, ${state.value.username}", style = AppTypography.Big.bold, color = Color.Black)

            SearchBar(modifier = Modifier
                .constrainAs(search) {
                    top.linkTo(zdravo.bottom)
                    start.linkTo(zdravo.start)
                    bottom.linkTo(jelodana.top)
                }
                .width(400.dp), state = state , events = events
            )



            MealOfTheDay(modifier = Modifier.constrainAs(jelodana){
                top.linkTo(search.bottom)
                start.linkTo(search.start)
            }, state = state)

            dontWantToOrder(modifier = Modifier.constrainAs(dontOrder){
                top.linkTo(jelodana.bottom)
                start.linkTo(jelodana.start)
            },
                onClickItem = { events.sendEvent(MenuEvents.dontWantToOrderClicked) }
            )

            Image(modifier = Modifier
                .constrainAs(avatar) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .padding(bottom = 4.dp)
                .clickable {
                    events.sendEvent(MenuEvents.profileAvatarClicked)
                }
                ,painter = painterResource(id = R.mipmap.avatar_dugme), contentDescription ="",
                contentScale = ContentScale.FillWidth)

            Image(modifier = Modifier
                .constrainAs(meni) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .clickable {}
                ,painter = painterResource(id = R.mipmap.meni_dugme), contentDescription ="",
                contentScale = ContentScale.FillWidth)

            Orders (modifier = Modifier
                .constrainAs(jelo) {
                    top.linkTo(dontOrder.bottom)}
                .height(360.dp),
                state = state,
                events = events)
        }
    }
    }


@Composable
fun Orders (modifier: Modifier, state: MutableState<MenuState>, events: ViewModelEvents<MenuEvents>){

    LazyColumn(modifier = modifier){
        items(items = state.value.listMenu){
            Card( modifier = Modifier
                .width(400.dp)
                .height(122.dp)
                .padding(bottom = 10.dp),
                shape = RoundedCornerShape(corner = CornerSize(20.dp)),
                backgroundColor = ColorComponents) {


                ConstraintLayout(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clickable {
                        events.sendEvent(MenuEvents.MealClicked(it.id.toString()))
                    }
                ){
                    val(jelo,cena)=createRefs()
                    MyText(modifier = Modifier.constrainAs(jelo)
                    {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, textAlign = TextAlign.Center,text = it.name.toString(), color = Color.Black, style = AppTypography.normal.bold)

                    Row (modifier = Modifier.padding(end=10.dp, bottom = 5.dp).constrainAs(cena){
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }) {
                        it.portions?.forEach{portion ->
                            if (portion.propertySize == "Velika"){
                                MyText(text = portion.price.toString(), color = Color.Black,style = AppTypography.normal.bold)
                            }
                        }

                        it.portions?.forEach{portion ->
                            if (portion.propertySize == "Mala"){
                                MyText(text = "/${portion.price.toString()}", color = Color.Black,style = AppTypography.normal.bold)
                            }
                        }

                        MyText(text = " RSD", color = Color.Black,style = AppTypography.normal.bold)
                    }
                }
            }
        }
    }
}

@Composable
fun MealOfTheDay (modifier: Modifier, state: MutableState<MenuState>){
    Card(modifier = modifier
        .width(400.dp)
        .height(185.dp),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        backgroundColor = ColorDugme) {
        Column(modifier = Modifier
            .width(400.dp)) {

           // state.value.listMenu.forEach(){}

            MyText(modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                text = "Jelo dana",
                style = AppTypography.Big.extraSize,
                color = ColorComponents,
                textAlign = TextAlign.Start)

            MyText(modifier = Modifier.padding(top = 10.dp,start = 80.dp),
                text = "Pileci Batak",
                style = AppTypography.Big.extraSize,
                color = ColorComponents,
                textAlign = TextAlign.Center)

            Row(modifier=Modifier.width(400.dp)) {
                Icon(painter = painterResource(R.mipmap.cook),
                    contentDescription = "",
                    modifier = Modifier.padding(start = 10.dp),
                    tint = ColorBackground)
                Column(modifier=Modifier.padding(start = 150.dp)) {
                    MyText(text = "200 RSD", color = ColorIcon, style = AppTypography.normal.bold,)
                    MyButton(
                        modifier = Modifier
                            .height(40.dp)
                            .width(140.dp)
                            .padding(),
                        text= "Poručite",
                        color = ColorPocetnoDugme,
                        onClick = {
                            //navController.navigate(route = ApplicationScreens.OrderScreen.name)
                        })
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    state: MutableState<MenuState>,
    events: ViewModelEvents<MenuEvents>
){

    InputField(
        modifier = modifier.padding(top = 14.dp, bottom = 14.dp),
        text = state.value.search,
        onTextChange = {
            events.sendEvent(MenuEvents.SearchUpdated(it))
        })
}

@Composable
fun InputField(
    modifier: Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Search,
    onAction: KeyboardActions = KeyboardActions.Default,

    onTextChange: (String) -> Unit,
    text: String = "") {

    TextField(
        value = text,
        shape = RoundedCornerShape(20.dp),
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = ColorComponents,
            cursorColor = Color.Black
        ),
        placeholder = { MyText(text = "Pretrazite jelo",
            style = AppTypography.normal.bold,
            color = ColorPocetnoDugme
        )},
        leadingIcon = {
            Icon(painter = painterResource(id = R.mipmap.search),
                contentDescription = "",
                tint = ColorIcon,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp))
        },
        maxLines=1,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        modifier = modifier
    )

}

@Composable
fun Search( modifier : Modifier ,onClickItem : () -> Unit = {}) {
    Card(
        modifier = modifier
            .width(400.dp)
            .height(90.dp)
            .padding(vertical = 20.dp)
            .clickable {
                onClickItem()
            },
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        backgroundColor = ColorComponents
    ) {
        Row() {
            Icon(
                painter = painterResource(id = R.mipmap.search),
                contentDescription = "",
                tint = ColorIcon,
                modifier = Modifier
                    .padding(start = 35.dp, top = 15.dp)
                    .width(20.dp)
                    .height(20.dp)
            )

            MyText(
                modifier = Modifier.padding(start = 25.dp, top = 15.dp),
                text = "Pretrazite jelo",
                style = AppTypography.normal.bold,
                color = ColorPocetnoDugme
            )
        }
    }
}




@Composable
fun dontWantToOrder( modifier : Modifier ,onClickItem : () -> Unit = {}){
    Card(modifier = modifier
        .width(400.dp)
        .height(90.dp)
        .padding(vertical = 20.dp)
        .clickable {
            onClickItem()
        },
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        backgroundColor = ColorComponents,
    ) {
        Row() {
            Icon(painter = painterResource(id = R.mipmap.x_icon),
                contentDescription = "",
                tint = ColorIcon,
                modifier = Modifier
                    .padding(start = 35.dp, top = 15.dp)
                    .width(20.dp)
                    .height(20.dp))

            MyText(modifier = Modifier.padding(start = 25.dp, top = 15.dp),
                text = "Danas ne želim da poručim",
                style = AppTypography.normal.bold,
                color = Color.Black)
        }
    }
}
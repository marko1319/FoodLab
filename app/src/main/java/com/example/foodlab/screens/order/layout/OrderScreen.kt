package com.example.foodlab.screens.order.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import com.example.foodlab.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.foodlab.core.components.MyButton
import com.example.foodlab.core.components.MyText
import com.example.foodlab.core.theme.AppTypography
import com.example.foodlab.core.theme.ColorBackground
import com.example.foodlab.core.theme.ColorDugme
import com.example.foodlab.core.utils.ViewModelEvents
import com.example.foodlab.screens.order.layout.elements.OrderRadioButtonItem
import com.example.foodlab.screens.order.layout.elements.OrderRadioButtonSmallItem
import com.example.foodlab.screens.order.viewModel.OrderEvents
import com.example.foodlab.screens.order.viewModel.OrderState

@Composable
fun OrderScreen(
    state: MutableState<OrderState> = mutableStateOf(OrderState()),
    events: ViewModelEvents<OrderEvents> = ViewModelEvents()
) {


    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = ColorBackground)
            .padding(30.dp)
    ) {

        val (exit, jelo, cena, prilozi, porcijaContainer, dugme, priloziContainer) = createRefs()

        Icon(painter = painterResource(R.mipmap.exit),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(exit) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .clickable {
                    events.sendEvent(OrderEvents.xPressed)
                }
                .padding(top = 30.dp),
            tint = ColorDugme)


        MyText(
            modifier = Modifier
                .constrainAs(jelo) {
                    top.linkTo(exit.bottom, margin = 50.dp)
                    start.linkTo(parent.start)

                },
            style = AppTypography.Big.bold,
            text = state.value.selectedFoodName ?: "No name",
            textAlign = TextAlign.Start, color = ColorDugme
        )

        MyText(
            modifier = Modifier
                .constrainAs(cena) {
                    top.linkTo(exit.bottom, margin = 50.dp)
                    end.linkTo(parent.end)
                },
            text = "${state.value.selectedFoodPrice}RSD",
            textAlign = TextAlign.Start, style = AppTypography.normal.bold, color = ColorDugme
        )

        Column(
            modifier = Modifier
            .constrainAs(porcijaContainer) {
                top.linkTo(jelo.bottom, margin = 100.dp)
                start.linkTo(parent.start)
            }) {

            MyText(
                text = "Porcija",
                textAlign = TextAlign.Start, style = AppTypography.normal.bold, color = ColorDugme
            )
            Spacer(modifier = Modifier.size(10.dp))
            LazyColumn(){

                state.value.selectedFoodPortions?.forEach{ element ->
                    item {
                        OrderRadioButtonItem(selected = element.second, text = element.first.propertySize, price = element.first.price.toString(), onClick = {})
                    }
                }

            }

        }

        Column(
            modifier = Modifier
                .constrainAs(priloziContainer) {
                    top.linkTo(porcijaContainer.bottom, margin = 26.dp)
                    start.linkTo(parent.start)
                }) {

            MyText(
                text = "Prilozi",
                textAlign = TextAlign.Start, style = AppTypography.normal.bold, color = ColorDugme
            )
            Spacer(modifier = Modifier.size(10.dp))
            LazyColumn(){

                state.value.selectedFoodAdditives?.forEach{ element ->
                    item {
                        OrderRadioButtonSmallItem(selected = element.second, text = element.first.name)
                    }
                }

            }

        }


        MyButton(
            modifier = Modifier
                .constrainAs(dugme) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(),
            color = ColorDugme,
            text = "Potvrdi porudžbinu",
            onClick = {
                events.sendEvent(OrderEvents.confirmOrder(state.value.orderRequest!!))

            })
    }
}
//
//@Composable
//fun RadioButtonUI(modifier: Modifier, state: MutableState<OrderState>,events: ViewModelEvents<OrderEvents>){
//    val radioOptions = listOf(state.value.meal.sideDishes?.forEach(){
//            it
//        }
//    )
//
////    val (selectedOption, onOptionSelected) = remember {
////        mutableStateOf(radioOptions[radioOptions.lastIndex].toString())
////    }
//
//    Column(modifier = modifier
//        .fillMaxWidth(),
//        verticalArrangement = Arrangement.Center
//    ) {
//        MyText(modifier = modifier
//            .fillMaxWidth(),
//            text = "Prilozi", textAlign = TextAlign.Start, style = AppTypography.normal.bold,
//            color = Color.Black)
//
//        Column {
//            state.value.meal.sideDishes?.forEach { text ->
//                    Row(
//                        Modifier
//                            .fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center,
//                    ) {
//
//                        Text(text = text.name.toString(), modifier = Modifier
//                            .padding(top = 10.dp)
//                            .width(150.dp), style = AppTypography.normal.medium)
//
//                        RadioButton(
//                            selected = (text.name == "Pire"),
//                            modifier = Modifier.padding(start = 70.dp),
//                            colors = RadioButtonDefaults.colors(selectedColor= ColorDugme,
//                                unselectedColor = ColorComponents),
//                            onClick = {
//                                events.sendEvent(OrderEvents.onSideDishesClick(text))
////                                onOptionSelected(text.name!!)
//
//                            })
//                    }
//            }
//            }
//        }
//    }
//
//
//@Composable
//fun Size(modifier: Modifier, events: ViewModelEvents<OrderEvents>, listOfPortion: List<Portion> ){
//    val selectedSize = remember {
//        mutableStateOf(Size.velika)
//    }
//
//
//
//    Column(modifier = modifier) {
//        MyText(modifier = modifier
//            .fillMaxWidth()
//            ,text = "Porcija", textAlign = TextAlign.Start,
//            style = AppTypography.normal.bold,
//            color = Color.Black)
//
//        listOfPortion.forEach {
//            Card(modifier = modifier
//                .width(350.dp)
//                .height(80.dp)
//                .padding(bottom = 10.dp)
//                .clickable {
//                    events.sendEvent(OrderEvents.onRBClick(it))
//                }
//                ,
//                shape = RoundedCornerShape(20.dp)) {
//                Row() {
//
//                    RadioButton(modifier = Modifier.padding(top=12.dp),
//                        selected = selectedSize.value == Size.velika,
//                        colors = RadioButtonDefaults.colors(selectedColor= Color.Black,
//                            unselectedColor = ColorComponents),
//                        onClick = {
//
//                        })
//
//                    Text(text= Size.velika,modifier = Modifier.padding(top = 24.dp) )
//                }
//            }
//        }
//
//
//
////        Card(modifier = modifier
////            .width(350.dp)
////            .height(80.dp)
////            .padding(bottom = 10.dp)
////            .clickable {
////                selectedSize.value = Size.mala
////                events.sendEvent(OrderEvents.onRBClick(selectedSize.value))
////            },
////            shape = RoundedCornerShape(20.dp)) {
////            Row(modifier = Modifier
////                .background(ColorDugme)
////                .padding(top = 12.dp)) {
////                RadioButton(selected = selectedSize.value == Size.mala,
////                    colors = RadioButtonDefaults.colors(selectedColor= Color.Black,
////                        unselectedColor = ColorComponents),
////                    onClick = {
////                    })
////                Text(text = Size.mala, color = ColorComponents, modifier = Modifier.padding(top = 11.dp))
////            }
////        }
//
//    }
//
//
//}

@Preview(showBackground = true)
@Composable
fun PreviewOrderScreen() {
    OrderScreen()
}

object Size {
    const val velika = "Velika"
    const val mala = "Mala"
}
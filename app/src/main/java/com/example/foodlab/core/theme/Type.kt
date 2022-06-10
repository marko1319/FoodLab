package com.example.foodlab.core.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.foodlab.R

private val montserrat : FontFamily =  FontFamily( Font(R.font.montserrat, FontWeight.Normal) )


open class AppTypography{
    companion object{
        val small: FontSet = FontSet(
            light = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp
            ),
            medium = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            ),
            bold = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            ),
            extraSize = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp
            ),
            extraBold = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 12.sp
            )
        )

        val normal: FontSet = FontSet(
            light = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp
            ),
            medium = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            ),
            bold = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            ),

            extraSize = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp
            ),
            extraBold = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp
            )
        )

        val Big: FontSet = FontSet(
            light = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Light,
                fontSize = 24.sp
            ),
            medium = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            ),
            bold = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            extraSize = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp),

            extraBold = TextStyle(
                fontFamily = montserrat,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
            )
        )

    }

}

data class FontSet(
    val light : TextStyle,
    val medium : TextStyle,
    val bold : TextStyle,
    val extraSize : TextStyle,
    val extraBold : TextStyle
)


// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
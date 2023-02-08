package com.bubblego.app.android.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bubblego.app.android.R

@Composable
fun BubbleCarTheme(
    //darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = lightColors(
        primary = Color(0xFF3662FF),
        primaryVariant = Color(0x213662FF),
        secondary = Color(0xFFD9D9D9),
        background = Color(0xFFFFFFFF),
        onSurface = Color(0xFF000000),
    )

    val typography = Typography(
        h1 = TextStyle(
            fontFamily = FontFamily(Font(R.font.airbnb_cereal_w_bd)),
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp
        ),
        h2 = TextStyle(
            fontFamily = FontFamily(Font(R.font.airbnb_cereal_w_md)),
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        ),
        body1 = TextStyle(
            fontFamily = FontFamily(Font(R.font.airbnb_cereal_w_lt)),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        body2 = TextStyle(
            fontFamily = FontFamily(Font(R.font.airbnb_cereal_w_lt)),
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        ),
        button = TextStyle(
            fontFamily = FontFamily(Font(R.font.airbnb_cereal_w_md)),
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(5.dp),
        large = RoundedCornerShape(36.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

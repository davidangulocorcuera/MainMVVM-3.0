package com.mainapp.mainapp.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.mainapp.mainapp.R

@Composable
fun ralewayRegular(style: FontStyle = FontStyle.Normal) = FontFamily(
    Font(
        resId = R.font.raleway_regular,
        weight = FontWeight.Normal,
        style = style
    )
)
@Composable
fun ralewayBold(style: FontStyle = FontStyle.Normal) = FontFamily(
    Font(
        resId = R.font.raleway_bold,
        weight = FontWeight.Bold,
        style = style
    )
)
@Composable
fun ralewayThin(style: FontStyle = FontStyle.Normal) = FontFamily(
    Font(
        resId = R.font.raleway_thin,
        weight = FontWeight.Thin,
        style = style
    )
)

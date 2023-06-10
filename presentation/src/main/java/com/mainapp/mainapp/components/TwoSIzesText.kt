package com.mainapp.mainapp.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.mainapp.mainapp.R

@Composable
fun TwoSizesText(
    size1: TextUnit,
    text1: String,
    size2: TextUnit,
    text2: String,
    modifier: Modifier = Modifier,
) {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                withStyle(style = SpanStyle(fontSize = size1)) {
                    append(text1)
                }
                withStyle(
                    SpanStyle(fontSize = size2)
                ) {
                    append(text2)
                }
            }
        },
        modifier,
        color = colorResource(R.color.white),
        fontFamily = ralewayBold()
    )
}

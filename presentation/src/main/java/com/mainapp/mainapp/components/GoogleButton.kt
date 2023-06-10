package com.mainapp.mainapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mainapp.mainapp.R

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CustomButton(
        modifier = modifier.wrapContentWidth(),
        backgroundColor = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(
                R.color.colorWhite
            )
        ), onClick = { onClick.invoke() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = "",
            tint = Color.Unspecified
        )
        Text(
            modifier = Modifier.padding(8.dp),
            fontSize = 18.sp,
            fontFamily = ralewayBold(),
            text = "Accede con Google", color = colorResource(R.color.colorLightGray)
        )
    }
}

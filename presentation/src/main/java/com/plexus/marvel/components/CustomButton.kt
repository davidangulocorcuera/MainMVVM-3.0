package com.plexus.marvel.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.plexus.marvel.R

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        shape = RoundedCornerShape(8.dp),
        onClick = {
            onClick.invoke()
        },
        elevation =  ButtonDefaults.elevation(
            defaultElevation = 4.dp,
            pressedElevation = 6.dp,
        ),
        modifier = modifier
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
            .wrapContentHeight()
               , colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.colorAccent))

    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text, color = colorResource(R.color.colorSuperLight)
        )
    }
}


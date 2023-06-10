package com.mainapp.mainapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mainapp.mainapp.R

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.colorAccent)),
    content: @Composable () -> Unit
) {
    Button(
        shape = RoundedCornerShape(8.dp),
        onClick = {
            onClick.invoke()
        },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 4.dp,
            pressedElevation = 6.dp,
        ),
        modifier = modifier
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
            .wrapContentHeight(), colors = backgroundColor

    ) {
        content()
    }
}

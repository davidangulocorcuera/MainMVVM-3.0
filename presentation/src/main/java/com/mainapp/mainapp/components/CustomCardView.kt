package com.mainapp.mainapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomCardView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    cornerRadius: Dp = 0.dp,
    elevation: Dp = 0.dp,
    image: String) {
    Surface(
        onClick = {onClick.invoke()},
        modifier = modifier.padding(16.dp),
        elevation = elevation,
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Column{
            Image(
                painter = rememberAsyncImagePainter(image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
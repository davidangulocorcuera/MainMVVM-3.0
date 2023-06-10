package com.mainapp.mainapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mainapp.mainapp.R

@Composable
fun BackgroundImage(
    id: Int,
) {
    Image(
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        painter = painterResource(id),
        contentDescription = null
    )
}

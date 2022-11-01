package com.mainapp.mainapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mainapp.mainapp.R

@Composable
fun CustomTopAppBar(
    title: String,
    onBackClick: () -> Unit,
    endActions: List<@Composable () -> Unit>? = null
) {
    TopAppBar(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = { onBackClick.invoke() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Ir hacia arriba")
            }
        },
        title = {
            Text(
                text = title
            )
        },
        actions = {
            endActions?.forEach { it.invoke() }
        },
        backgroundColor = colorResource(id = R.color.colorAccent)
    )
}
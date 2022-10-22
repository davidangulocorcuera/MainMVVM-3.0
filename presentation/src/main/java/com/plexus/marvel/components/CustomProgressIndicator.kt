package com.plexus.marvel.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressIndicator() {
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(1000))
    )

    Box{
        CircularProgressIndicator(
            progress = progressAnimationValue,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.Center)
        )
    }
}
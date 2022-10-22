package com.plexus.marvel.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.*

@Composable
fun CustomLoading(anim: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(anim))
    val progress by animateLottieCompositionAsState(composition)

    LottieAnimation(
        composition = composition,
        progress = { progress },
    )

}
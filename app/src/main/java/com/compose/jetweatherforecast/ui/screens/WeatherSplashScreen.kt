package com.compose.jetweatherforecast.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.compose.jetweatherforecast.R
import com.compose.jetweatherforecast.ui.navigation.WeatherScreens
import com.compose.jetweatherforecast.utils.*
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavHostController) {

    val scale = remember {
        Animatable(0F)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f).getInterpolation(it)
                })
        )
        delay(2000L)
        navController.navigate("${WeatherScreens.MainScreen.name}/${Constants.DEFAULT_CITY}")
    }

    Surface(
        modifier = Modifier
            .padding(splashSurfacePadding)
            .size(splashSurfaceSize)
            .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = splashSurfaceBorderWidth, color = Color.LightGray)
    ) {

        Column(
            modifier = Modifier.padding(splashColumnPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.ic_sun),
                contentDescription = stringResource(R.string.desc_sun_icon),
                modifier = Modifier.size(splashIconSize),
                contentScale = ContentScale.Fit
            )
            Text(
                text = stringResource(R.string.splash_message),
                style = MaterialTheme.typography.h5,
                color = Color.LightGray
            )

        }

    }

}
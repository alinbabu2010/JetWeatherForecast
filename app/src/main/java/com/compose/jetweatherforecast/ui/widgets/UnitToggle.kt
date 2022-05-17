package com.compose.jetweatherforecast.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import com.compose.jetweatherforecast.ui.theme.Magenta400
import com.compose.jetweatherforecast.utils.ClearRippleTheme
import com.compose.jetweatherforecast.utils.TemperatureUnit
import com.compose.jetweatherforecast.utils.settingsToggleButtonPadding


@Composable
fun UnitToggleButton(
    unitToggleState: MutableState<Boolean>,
    choiceState: MutableState<TemperatureUnit>
) {

    CompositionLocalProvider(
        LocalRippleTheme provides ClearRippleTheme
    ) {
        IconToggleButton(
            checked = unitToggleState.value, onCheckedChange = {
                unitToggleState.value = it
                choiceState.value = if (unitToggleState.value) {
                    TemperatureUnit.Imperial
                } else TemperatureUnit.Metric

            }, modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(settingsToggleButtonPadding)
                .background(Magenta400)
                .clip(RectangleShape)
        ) {

            Text(
                text = if (unitToggleState.value) {
                    TemperatureUnit.Imperial.unit
                } else TemperatureUnit.Metric.unit
            )

        }
    }
}
package com.compose.jetweatherforecast.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.compose.jetweatherforecast.R
import com.compose.jetweatherforecast.data.model.WeatherItem
import com.compose.jetweatherforecast.ui.theme.Grey100
import com.compose.jetweatherforecast.ui.theme.Yellow100
import com.compose.jetweatherforecast.utils.*

@Composable
fun WeatherDetails(weatherItemList: List<WeatherItem>) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Grey100,
        shape = RoundedCornerShape(detailSurfaceCorner)
    ) {

        LazyColumn(
            modifier = Modifier.padding(detailListPadding),
            contentPadding = PaddingValues(detailListContentPadding)
        ) {

            items(weatherItemList) { item: WeatherItem ->
                WeatherDetailRow(item)
            }

        }

    }

}

@Composable
fun WeatherDetailRow(weatherItem: WeatherItem) {

    val weatherObject = weatherItem.weather.first()
    val imageUrl = stringResource(R.string.image_url, weatherObject.icon)

    Surface(
        modifier = Modifier
            .padding(detailRowSurfacePadding)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(detailRowSurfaceCornerSize)),
        color = Color.White
    ) {

        Row(
            modifier = Modifier
                .padding(
                    start = detailRowDataPadding,
                    end = detailRowDataPadding
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = formatDay(weatherItem.dt))
            WeatherStateImage(imageUrl)
            Surface(shape = CircleShape, color = Yellow100) {
                Text(
                    text = weatherObject.description,
                    modifier = Modifier.padding(detailRowDescTextPadding),
                    style = MaterialTheme.typography.caption
                )
            }
            Text(text = getMinAndMaxWeatherTemp(weatherItem.temp))

        }

    }

}

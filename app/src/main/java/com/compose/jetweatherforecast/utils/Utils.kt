package com.compose.jetweatherforecast.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.compose.jetweatherforecast.data.model.Temp
import java.text.SimpleDateFormat
import java.util.*

/**
 * To format given time stamp to [Constants.DATE_FORMAT]
 * @param timestamp TimeStamp to be formatted
 * @return Formatted date as [String] value
 */
fun formatDate(timestamp: Int): String {
    return SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault())
        .format(Date(timestamp.toLong() * 1000))
}

/**
 * To format given time stamp to specific day
 * @param timestamp TimeStamp to be formatted
 * @return Day for that timeStamp
 */
fun formatDay(timestamp: Int): String {
    return formatDate(timestamp).split(",").first()
}

/**
 * To format given time stamp to [Constants.TIME_FORMAT]
 * @param timestamp TimeStamp to be formatted
 * @return Formatted time as [String] value
 */
fun formatDateTime(timestamp: Int): String {
    return SimpleDateFormat(Constants.TIME_FORMAT, Locale.getDefault())
        .format(Date(timestamp.toLong() * 1000))
}

/**
 * To format decimals to string literal
 * @param item Decimal value to be formatted
 * @return The string literal
 */
fun formatDecimals(item: Double): String {
    return " %.0f".format(item)
}

/**
 * To add degree symbol to end of string
 * @param text Text needed to add degree symbol
 * @return The formatted text
 */
fun formatWithDegreeSymbol(text: String) = text + 0x00B0.toChar()

/**
 * To get annotatedString for min and max temperature to display
 * @param temp An object [Temp]
 * @return AnnotatedString to be displayed
 */
fun getMinAndMaxWeatherTemp(temp: Temp) = buildAnnotatedString {

    withStyle(
        style = SpanStyle(
            color = Color.Blue.copy(alpha = 0.7f),
            fontWeight = FontWeight.SemiBold
        )
    ) {
        append(formatWithDegreeSymbol(formatDecimals(temp.max)))
    }

    withStyle(
        style = SpanStyle(
            color = Color.LightGray,
            fontWeight = FontWeight.SemiBold
        )
    ) {
        append(formatWithDegreeSymbol(formatDecimals(temp.min)))
    }

}
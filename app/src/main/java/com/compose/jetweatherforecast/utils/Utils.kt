package com.compose.jetweatherforecast.utils

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
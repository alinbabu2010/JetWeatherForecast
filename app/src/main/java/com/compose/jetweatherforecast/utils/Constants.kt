package com.compose.jetweatherforecast.utils

object Constants {

    const val WEATHER_ENDPOINT = "/data/2.5/forecast/daily"

    const val PARAM_QUERY = "q"
    const val PARAM_UNITS = "units"
    const val PARAM_APPID = "appid"

    const val DEFAULT_CITY = "Seattle"
    const val UNIT_METRIC = "metric"
    const val UNIT_IMPERIAL = "imperial"

    const val NOT_FOUND = "Location not found"
    const val NETWORK_FAILURE = "Network error occurred. Please try later"

    const val DATE_FORMAT = "EEE, MMM d"
    const val TIME_FORMAT = "hh:mm:aa"

    const val NAV_ARG_CITY = "city"

    const val DELIMITER_COMMA = ","

    const val DATASTORE_NAME = "settings"
    const val PREF_NAME = "degree_metrics"

}
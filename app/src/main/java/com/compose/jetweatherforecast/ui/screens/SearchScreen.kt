package com.compose.jetweatherforecast.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import com.compose.jetweatherforecast.R
import com.compose.jetweatherforecast.ui.navigation.WeatherScreens
import com.compose.jetweatherforecast.ui.widgets.CommonTextField
import com.compose.jetweatherforecast.ui.widgets.WeatherAppBar
import com.compose.jetweatherforecast.utils.Constants
import com.compose.jetweatherforecast.utils.searchBarPadding
import com.compose.jetweatherforecast.utils.validateText

@Composable
fun SearchScreen(navController: NavController) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = stringResource(R.string.search),
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false
        ) {
            navController.popBackStack()
        }
    }) {

        Surface {
            SearchBar {
                navController.navigate(WeatherScreens.MainScreen.name + "/$it")
            }
        }

    }

}


@Composable
fun SearchBar(onSearch: (String) -> Unit) {

    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    val isValid = remember(searchQueryState.value) {
        searchQueryState.value.validateText()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(searchBarPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonTextField(valueState = searchQueryState,
            placeHolder = Constants.DEFAULT_CITY,
            imeAction = ImeAction.Done,
            onAction = KeyboardActions {
                if (!isValid) return@KeyboardActions
                onSearch.invoke(searchQueryState.value)
                searchQueryState.value = ""
                focusManager.clearFocus()
            })
    }

}


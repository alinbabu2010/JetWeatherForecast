package com.compose.jetweatherforecast.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.compose.jetweatherforecast.R
import com.compose.jetweatherforecast.utils.MenuItems
import com.compose.jetweatherforecast.utils.dropDownColumnPaddingRight
import com.compose.jetweatherforecast.utils.dropDownColumnPaddingTop
import com.compose.jetweatherforecast.utils.dropDownMenuWidth

@Composable
fun ShowDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.TopEnd)
            .absolutePadding(
                top = dropDownColumnPaddingTop,
                right = dropDownColumnPaddingRight
            )
    ) {

        DropdownMenu(
            showDialog.value, onDismissRequest = { showDialog.value = false },
            modifier = Modifier
                .width(dropDownMenuWidth)
                .background(Color.White)
        ) {

            enumValues<MenuItems>().forEach { menuItem ->

                val itemIcon = when (menuItem) {
                    MenuItems.About -> Icons.Default.Info
                    MenuItems.Favorites -> Icons.Default.FavoriteBorder
                    MenuItems.Settings -> Icons.Default.Settings
                }

                DropdownMenuItem(onClick = {
                    showDialog.value = false
                }) {
                    Icon(
                        imageVector = itemIcon,
                        contentDescription = stringResource(
                            R.string.desc_dropdown_icons,
                            menuItem.name
                        ),
                        tint = Color.LightGray
                    )
                    Text(text = menuItem.name, fontWeight = FontWeight(300))
                }

            }

        }

    }

}
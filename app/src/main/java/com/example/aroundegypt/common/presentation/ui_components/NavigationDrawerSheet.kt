package com.example.aroundegypt.common.presentation.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.R
import com.example.aroundegypt.common.presentation.ui.theme.white30

@Composable
fun NavigationDrawerSheet(onCloseDrawerSheetClick: () -> Unit = {}) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp
    val isLandscape = screenWidthDp > screenHeightDp
    val drawerWidthPercent =
        if (isLandscape) 0.3f else 0.7f // Adjust the width based on orientation

    ModalDrawerSheet(
        modifier = Modifier.fillMaxWidth(drawerWidthPercent),
        drawerContainerColor = Color.Black
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 20.dp, end = 10.dp, top = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ImageIcon(
                    icon = R.drawable.ic_logo,
                    height = 70.dp,
                    contentDescription = stringResource(R.string.app_icon)
                )
                VectorIcon(
                    icon = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close),
                    size = 35.dp,
                    color = Color.White,
                    modifier = Modifier.clickable { onCloseDrawerSheetClick() }
                )
            }
            HorizontalDivider(
                color = Color.White,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
            )
            NavigationDrawerItem(text = "Top Cities", showExpandIcon = true)
            HorizontalDivider(
                color = white30,
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            NavigationDrawerItem(text = "Tags", showExpandIcon = true)
            HorizontalDivider(
                color = white30,
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            NavigationDrawerItem(text = "About Us", showExpandIcon = false)
            HorizontalDivider(
                color = white30,
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            NavigationDrawerItem(text = "Contact Us", showExpandIcon = false)
            HorizontalDivider(
                color = white30,
                thickness = 0.5.dp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            NavigationDrawerItem(
                text = "Language",
                showExpandIcon = true,
                startIcon = {
                    VectorResIcon(
                        icon = R.drawable.ic_language,
                        contentDescription = stringResource(R.string.language),
                        height = 30.dp
                    )
                })
        }

    }
}

@Composable
private fun NavigationDrawerItem(
    modifier: Modifier = Modifier,
    text: String,
    showExpandIcon: Boolean,
    onExpandIconClick: () -> Unit = {},
    startIcon: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (startIcon != null)
                startIcon()
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
        if (showExpandIcon)
            IconButton({ onExpandIconClick() }) {
                VectorIcon(
                    icon = Icons.Default.KeyboardArrowDown, contentDescription = stringResource(
                        R.string.expand_list
                    ), size = 30.dp, color = Color.White
                )
            }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewNavigationDrawerSheet() {
    NavigationDrawerSheet()
}

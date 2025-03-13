package com.example.aroundegypt.common.presentation.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp

@Composable
fun ImageIcon(modifier: Modifier = Modifier, icon: Int, contentDescription: String, height: Dp) {
    Image(
        painter = painterResource(icon),
        contentDescription = contentDescription,
        modifier = modifier.height(height)
    )
}


@Composable
fun VectorResIcon(
    modifier: Modifier = Modifier,
    icon: Int,
    contentDescription: String,
    height: Dp
) {
    Image(
        imageVector = ImageVector.vectorResource(icon),
        contentDescription = contentDescription,
        modifier = modifier.height(height = height)
    )
}

@Composable
fun VectorIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String,
    size: Dp,
    color: Color
) {
    Image(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier.size(size = size),
        colorFilter = ColorFilter.tint(color),
    )
}


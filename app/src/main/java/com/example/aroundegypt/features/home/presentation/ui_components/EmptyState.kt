package com.example.aroundegypt.features.home.presentation.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.common.presentation.ui.theme.Gotham
import com.example.aroundegypt.common.presentation.ui_components.ImageIcon

@Composable
fun EmptyState(
    iconResId: Int,
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ImageIcon(
            icon = iconResId,
            height = 200.dp,
            contentDescription = title
        )
        Text(
            text = title,
            fontFamily = Gotham,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = message,
            fontFamily = Gotham,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color.LightGray
        )
    }
}
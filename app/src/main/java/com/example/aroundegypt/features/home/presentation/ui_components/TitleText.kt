package com.example.aroundegypt.features.home.presentation.ui_components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.common.presentation.ui.theme.Gotham

@Composable
fun TitleText(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title,
        fontFamily = Gotham,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    )
}
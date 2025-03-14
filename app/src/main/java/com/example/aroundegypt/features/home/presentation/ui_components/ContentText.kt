package com.example.aroundegypt.features.home.presentation.ui_components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.LineHeightStyle.Trim
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.common.presentation.ui.theme.Gotham

@Composable
fun ContentText(
    modifier: Modifier = Modifier,
    content: String,
    fontSize: TextUnit = 14.sp,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Text(
        modifier = modifier,
        text = content,
        fontFamily = Gotham,
        fontWeight = FontWeight.Medium,
        fontSize = fontSize,
        color = color,
        style = TextStyle(
            lineHeight = 17.sp,
            baselineShift = BaselineShift(1F),
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Proportional,
                trim = Trim.Both
            )
        )
    )
}
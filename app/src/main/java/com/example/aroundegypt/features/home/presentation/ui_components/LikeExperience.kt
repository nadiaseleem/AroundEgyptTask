package com.example.aroundegypt.features.home.presentation.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.R
import com.example.aroundegypt.common.presentation.ui.theme.Gotham
import com.example.aroundegypt.common.presentation.ui_components.VectorResIcon
import com.example.aroundegypt.features.home.domain.models.Experience

@Composable
fun LikeExperience(
    modifier: Modifier = Modifier,
    experience: Experience,
    onLikeClick: (Experience) -> Unit,
    fontSize: TextUnit = 14.sp,
    isIconFirst: Boolean = false
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isIconFirst) {
            LikeIcon(experience, onLikeClick)
            LikesCount(experience, fontSize)
        } else {
            LikesCount(experience, fontSize)
            LikeIcon(experience, onLikeClick)
        }
    }
}

@Composable
private fun LikesCount(experience: Experience, fontSize: TextUnit = 14.sp) {
    Text(
        text = "${experience.likesNo}",
        modifier = Modifier.wrapContentWidth(),
        fontFamily = Gotham,
        fontWeight = FontWeight.Medium,
        fontSize = fontSize
    )
}

@Composable
private fun LikeIcon(
    experience: Experience,
    onLikeClick: (Experience) -> Unit
) {
    VectorResIcon(
        icon = if (!experience.isLiked) R.drawable.ic_outlined_like else R.drawable.ic_filed_like,
        height = 18.dp,
        contentDescription = stringResource(R.string.like),
        modifier = Modifier
            .padding(end = 10.dp)
            .clickable(enabled = !experience.isLiked) {
                onLikeClick(experience)
            }
    )
}

@Preview(showBackground = true)
@Composable
private fun LikeExperiencePreview() {
    LikeExperience(experience = experience, onLikeClick = {})

}
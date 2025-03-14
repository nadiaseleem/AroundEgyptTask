package com.example.aroundegypt.features.home.presentation.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.features.home.domain.models.ExperiencesResponse

@Composable
fun ExperienceList(
    experiences: List<ExperiencesResponse.Experience>,
    isHorizontal: Boolean = false,
    isRecommended: Boolean = false,
    onLikeClick: (ExperiencesResponse.Experience) -> Unit,
    modifier: Modifier = Modifier
) {
    if (isHorizontal) {
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(experiences) { experience ->
                ExperienceCard(
                    modifier = Modifier.width(320.dp),
                    experience = experience,
                    isRecommended = isRecommended,
                    onLikeClick = { onLikeClick(it) }
                )
            }
        }
    } else {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(experiences) { experience ->
                ExperienceCard(
                    experience = experience,
                    modifier = Modifier.padding(end = 18.dp)
                ) { onLikeClick(it) }
            }
        }
    }
}
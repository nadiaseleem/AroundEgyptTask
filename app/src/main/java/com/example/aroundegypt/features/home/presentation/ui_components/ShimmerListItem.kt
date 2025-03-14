package com.example.aroundegypt.features.home.presentation.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.common.presentation.shimmer_effect_modifier.shimmerEffect

@Composable
fun ShimmerListItem(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .width(320.dp)
                .height(164.dp)
                .shimmerEffect()
        )

        Row(
            modifier = Modifier.width(320.dp), horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Box(
                modifier = Modifier
                    .height(13.dp)
                    .width(99.dp)
                    .shimmerEffect()
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(13.dp)
                        .width(57.dp)
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .height(13.dp)
                        .width(13.dp)
                        .shimmerEffect()
                )
            }
        }

    }
}
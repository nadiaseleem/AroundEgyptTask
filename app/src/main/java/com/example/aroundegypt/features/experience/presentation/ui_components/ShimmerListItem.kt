package com.example.aroundegypt.features.experience.presentation.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.common.presentation.shimmer_effect_modifier.shimmerEffect
@Composable
fun ShimmerListItem(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(214.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Image shimmer - uses fillMaxWidth to be responsive
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(164.dp)
                .clip(RoundedCornerShape(10.dp))
                .shimmerEffect()
        )

        // Title and like section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Title shimmer
            Box(
                modifier = Modifier
                    .weight(2f)
                    .height(13.dp)
                    .shimmerEffect()
            )

            // Like section
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Like count shimmer
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(13.dp)
                        .shimmerEffect()
                )

                // Like icon shimmer
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .height(18.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}
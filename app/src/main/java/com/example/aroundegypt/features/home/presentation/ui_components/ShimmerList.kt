package com.example.aroundegypt.features.home.presentation.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerList(
    isLoading: Boolean = true,
    itemCount: Int = 5,
    isHorizontal: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(end = 18.dp),
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        if (isHorizontal) {
            LazyRow(
                modifier = modifier,
                contentPadding = contentPadding,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(itemCount) {
                    ShimmerCard(modifier = Modifier.width(320.dp))
                }
            }
        } else {
            LazyColumn(
                modifier = modifier,
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(itemCount) {
                    ShimmerCard()
                }
            }
        }
    }
}
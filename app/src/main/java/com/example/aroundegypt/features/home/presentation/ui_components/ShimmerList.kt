package com.example.aroundegypt.features.home.presentation.ui_components

import androidx.compose.foundation.layout.Arrangement
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
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        if (isHorizontal) {
            LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(itemCount) {
                    ShimmerListItem()
                }
            }
        } else {
            LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(itemCount) {
                    ShimmerListItem()
                }
            }
        }
    }
}
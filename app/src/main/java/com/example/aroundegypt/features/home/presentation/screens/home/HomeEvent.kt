package com.example.aroundegypt.features.home.presentation.screens.home

import com.example.aroundegypt.common.presentation.events.UIEvent

sealed class HomeEvent : UIEvent {
    data class ShowError(val message: String) : HomeEvent()
}
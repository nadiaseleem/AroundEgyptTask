package com.example.aroundegypt.features.home.presentation

import com.example.aroundegypt.common.presentation.events.UIEvent

sealed class HomeEvent : UIEvent {
    data class ShowError(val message: String) : HomeEvent()
}
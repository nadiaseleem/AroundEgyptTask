package com.example.aroundegypt.features.home.presentation.screens.experience

import com.example.aroundegypt.common.presentation.events.UIEvent

sealed class ExperienceEvent : UIEvent {
    data class ShowError(val message: String) : ExperienceEvent()
}
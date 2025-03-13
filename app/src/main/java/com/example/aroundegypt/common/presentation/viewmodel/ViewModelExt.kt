package com.example.aroundegypt.common.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.common.presentation.events.EventBus
import com.example.aroundegypt.common.presentation.events.UIEvent
import kotlinx.coroutines.launch

fun ViewModel.sendEvent(event: UIEvent) {
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}
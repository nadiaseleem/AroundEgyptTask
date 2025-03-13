package com.example.aroundegypt.common.presentation.events

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object EventBus {
    private val _events = Channel<UIEvent>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: UIEvent) {
        _events.send(event)
    }
}

package com.example.foodlab.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ViewModelEvents<T>(viewModelScope: CoroutineScope? = null) {
    private val scope = viewModelScope
    private val eventChannel = Channel<T>(Channel.BUFFERED)

    val eventsFlow = eventChannel.receiveAsFlow()

    fun sendEvent(event: T) {
        scope?.launch {
            eventChannel.send(event)
        }
    }

    fun listenForEvents(run: (event: T) -> Unit) {
        scope?.launch {
            eventsFlow.collect { event ->
                run(event)
            }
        }
    }
}
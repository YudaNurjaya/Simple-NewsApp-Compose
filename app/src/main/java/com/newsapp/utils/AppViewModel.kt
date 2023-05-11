package com.newsapp.utils

import org.orbitmvi.orbit.Container

interface AppViewModel<INTENT : Any, STATE : Any, SIDE_EFFECT : Any> : Container<STATE, SIDE_EFFECT> {
    fun onEventDispatcher(intent: INTENT)
}
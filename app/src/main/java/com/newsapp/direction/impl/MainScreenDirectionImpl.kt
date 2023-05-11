package com.newsapp.direction.impl

import cafe.adriel.voyager.navigator.Navigator
import com.newsapp.direction.MainScreenDirection
import javax.inject.Inject

class MainScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
): MainScreenDirection {
    override suspend fun navigateToMainDetail() {

    }
}
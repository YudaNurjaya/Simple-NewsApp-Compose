package com.newsapp.direction.impl

import cafe.adriel.voyager.navigator.Navigator
import com.newsapp.direction.SplashScreenDirection
import javax.inject.Inject

class SplashScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
): SplashScreenDirection {
    override suspend fun navigateToMain() {
    }
}
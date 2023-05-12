package com.newsapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Source
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuNav(val route: String, val icon: ImageVector, val title: String) {

    object TopNews: BottomMenuNav("TopNews",
        icon = Icons.Outlined.Home, "Home")

    object Categories: BottomMenuNav("Categories",
        icon = Icons.Outlined.List, "Categories")

    object Sources: BottomMenuNav("Sources",
        icon = Icons.Outlined.Source, "Sources")
}
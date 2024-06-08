package com.geovnn.meteoapuane.presentation.home

import com.geovnn.meteoapuane.domain.models.HomePage

data class HomeState(
    val homePage: HomePage = HomePage(),
    val isLoading: Boolean = true,
    val error: String = "",
)

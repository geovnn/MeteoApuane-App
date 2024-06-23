package com.geovnn.meteoapuane.presentation.incendi

import com.geovnn.meteoapuane.domain.models.IncendiPage

data class IncendiState(
    val incendiPage: IncendiPage = IncendiPage(),
    val isLoading: Boolean = true,
    val error: String = "",
)

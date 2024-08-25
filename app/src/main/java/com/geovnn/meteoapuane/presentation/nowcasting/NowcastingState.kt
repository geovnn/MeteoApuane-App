package com.geovnn.meteoapuane.presentation.nowcasting

import com.geovnn.meteoapuane.domain.models.NowcastingPage

data class NowcastingState(
    val nowcastingPage: NowcastingPage = NowcastingPage(),
    val isLoading: Boolean = true,
    val error: String = "",
)

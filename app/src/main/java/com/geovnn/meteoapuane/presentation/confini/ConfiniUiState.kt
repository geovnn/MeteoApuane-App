package com.geovnn.meteoapuane.presentation.confini

import com.geovnn.meteoapuane.domain.models.ConfiniPage

data class ConfiniUiState(
    val error: String = "",
    val isLoading: Boolean = true,
    val confiniPage: ConfiniPage = ConfiniPage()
)

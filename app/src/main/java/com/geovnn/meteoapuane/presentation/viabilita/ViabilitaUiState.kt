package com.geovnn.meteoapuane.presentation.viabilita

import com.geovnn.meteoapuane.domain.models.ViabilitaPage

data class ViabilitaUiState(
    val error: String = "",
    val isLoading: Boolean = true,
    val viabilitaPage: ViabilitaPage = ViabilitaPage()
    )
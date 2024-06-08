package com.geovnn.meteoapuane.presentation.provincia

import com.geovnn.meteoapuane.domain.models.ProvinciaPage

data class ProvinciaUiState(
    val error: String = "",
    val isLoading: Boolean = true,
    val provinciaPage: ProvinciaPage = ProvinciaPage()
    )

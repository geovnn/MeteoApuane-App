package com.geovnn.meteoapuane.presentation.montagna

import com.geovnn.meteoapuane.domain.models.MontagnaPage

data class MontagnaUiState(
    val error: String = "",
    val isLoading: Boolean = true,
    val montagnaPage: MontagnaPage = MontagnaPage(),
    )

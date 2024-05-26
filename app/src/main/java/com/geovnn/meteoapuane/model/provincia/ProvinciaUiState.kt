package com.geovnn.meteoapuane.model.provincia

data class ProvinciaUiState(
    val error: String = "",
    val isLoading: Boolean = true,
    val testoUltimoAggiornamento: String = "",
    val paginaOggi: ProvinciaPagina = ProvinciaPagina(),
    val paginaDomani: ProvinciaPagina = ProvinciaPagina(),
    val paginaDopodomani: ProvinciaPagina = ProvinciaPagina(),
    val paginaSuccessivi: ProvinciaPaginaSuccessivi = ProvinciaPaginaSuccessivi(),
    )

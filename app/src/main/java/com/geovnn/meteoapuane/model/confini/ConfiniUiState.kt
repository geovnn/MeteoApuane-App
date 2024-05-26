package com.geovnn.meteoapuane.model.confini

data class ConfiniUiState(
    val error: String = "",
    val isLoading: Boolean = true,
    val testoUltimoAggiornamento: String = "",
    val testoPrevisione: String = "",
    val paginaOggi: ConfiniPagina = ConfiniPagina(),
    val paginaDomani: ConfiniPagina = ConfiniPagina(),
    val paginaDopodomani: ConfiniPagina = ConfiniPagina(),
)

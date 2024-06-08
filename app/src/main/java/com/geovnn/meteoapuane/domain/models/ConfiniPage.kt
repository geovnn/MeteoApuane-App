package com.geovnn.meteoapuane.domain.models

data class ConfiniPage(
    val testoUltimoAggiornamento: String = "",
    val testoPrevisione: String = "",
    val paginaOggi: ConfiniPageTab = ConfiniPageTab(),
    val paginaDomani: ConfiniPageTab = ConfiniPageTab(),
    val paginaDopodomani: ConfiniPageTab = ConfiniPageTab(),
)

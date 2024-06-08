package com.geovnn.meteoapuane.domain.models

data class ProvinciaPage(
    val testoUltimoAggiornamento: String = "",
    val paginaOggi: ProvinciaPageTab = ProvinciaPageTab(),
    val paginaDomani: ProvinciaPageTab = ProvinciaPageTab(),
    val paginaDopodomani: ProvinciaPageTab = ProvinciaPageTab(),
    val paginaSuccessivi: ProvinciaPageSuccessivi = ProvinciaPageSuccessivi(),
)

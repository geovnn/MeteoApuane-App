package com.geovnn.meteoapuane.domain.models

data class NowcastingPage(
    val meteosatInfrarosso: String = "",
    val meteosatInfrarossoAnimazione: String = "",
    val temperaturaNubi: String = "",
    val meteosatVisibileAnimazione: String = "",
    val fulminazioniAnimazioneGolfoLigure: String = "",
    val fulminazioniAnimazioneItalia: String = "",
    val radarPrecipitazioniSettepani: String = "",
    val radarPrecipitazioniMeteoFrance: String = "",
    val cartaSinotticaEuropa: String = "",
    val cartaSinotticaItalia: String = "",
)

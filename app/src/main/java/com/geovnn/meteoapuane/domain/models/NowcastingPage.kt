package com.geovnn.meteoapuane.domain.models

import android.graphics.Bitmap

data class NowcastingPage(
    val meteosatInfrarosso: Bitmap? = null,
    val meteosatInfrarossoAnimazione: Bitmap? = null,
    val temperaturaNubi: Bitmap? = null,
    val meteosatVisibileAnimazione: Bitmap? = null,
    val fulminazioniAnimazioneGolfoLigure: Bitmap? = null,
    val fulminazioniAnimazioneItalia: Bitmap? = null,
    val radarPrecipitazioniSettepani: Bitmap? = null,
    val radarPrecipitazioniMeteoFrance: Bitmap? = null,
    val cartaSinotticaEuropa: Bitmap? = null,
    val cartaSinotticaItalia: Bitmap? = null,
)

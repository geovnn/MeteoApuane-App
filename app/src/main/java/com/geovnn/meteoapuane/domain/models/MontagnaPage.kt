package com.geovnn.meteoapuane.domain.models

import android.graphics.Bitmap

data class MontagnaPage(
    val testoUltimoAggiornamento : String = "",
    val testo: String = "",
    val testoDataOggi: String = "",
    val testoDataDomani: String = "",
    val testoDataDopodomani: String = "",
    val testoZeroZeri: String = "",
    val testoNeveZeri: String = "",
    val immagineSfondo: String = "",
    val immagineOggiZeri: String = "",
    val immagineDomaniZeri: String = "",
    val immagineDopodomaniZeri: String = "",
    val testoZeroPratospilla: String = "",
    val testoNevePratospilla: String = "",
    val immagineOggiPratospilla: String = "",
    val immagineDomaniPratospilla: String = "",
    val immagineDopodomaniPratospilla: String = "",
    val testoZeroCampocecina: String = "",
    val testoNeveCampocecina: String = "",
    val immagineOggiCampocecina: String = "",
    val immagineDomaniCampocecina: String = "",
    val immagineDopodomaniCampocecina: String = "",
    val testoZeroCerreto: String = "",
    val testoNeveCerreto: String = "",
    val immagineOggiCerreto: String = "",
    val immagineDomaniCerreto: String = "",
    val immagineDopodomaniCerreto: String = "",
)

package com.geovnn.meteoapuane.model

import android.graphics.Bitmap

data class MontagnaUiState(
    val error: String = "",
    val isLoading: Boolean = true,
    val testoUltimoAggiornamento : String = "",
    val testo: String = "",
    val testoDataOggi: String = "",
    val testoDataDomani: String = "",
    val testoDataDopodomani: String = "",
    val testoZeroZeri: String = "",
    val testoNeveZeri: String = "",
    val immagineSfondo: Bitmap? = null,
    val immagineOggiZeri: Bitmap? = null,
    val immagineDomaniZeri: Bitmap? = null,
    val immagineDopodomaniZeri: Bitmap? = null,
    val testoZeroPratospilla: String = "",
    val testoNevePratospilla: String = "",
    val immagineOggiPratospilla: Bitmap? = null,
    val immagineDomaniPratospilla: Bitmap? = null,
    val immagineDopodomaniPratospilla: Bitmap? = null,
    val testoZeroCampocecina: String = "",
    val testoNeveCampocecina: String = "",
    val immagineOggiCampocecina: Bitmap? = null,
    val immagineDomaniCampocecina: Bitmap? = null,
    val immagineDopodomaniCampocecina: Bitmap? = null,
    val testoZeroCerreto: String = "",
    val testoNeveCerreto: String = "",
    val immagineOggiCerreto: Bitmap? = null,
    val immagineDomaniCerreto: Bitmap? = null,
    val immagineDopodomaniCerreto: Bitmap? = null,
    )

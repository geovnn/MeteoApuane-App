package com.geovnn.meteoapuane.domain.models

import android.graphics.Bitmap

data class ProvinciaPageSuccessivi(
    val label : String = "",
    val testo : String = "",
    val data1 : String = "",
    val imgA1: Bitmap? = null,
    val imgB1: Bitmap? = null,
    val imgC1: Bitmap? = null,
    val attendibilita1 : String = "",
    val data2 : String = "",
    val imgA2: Bitmap? = null,
    val imgB2: Bitmap? = null,
    val imgC2: Bitmap? = null,
    val attendibilita2 : String = "",
)

package com.geovnn.meteoapuane.domain.models

import android.graphics.Bitmap

data class IncendiPage(
    val dataOggi: String = "",
    val costaOggi: Bitmap? = null,
    val lunigianaOggi: Bitmap? = null,
    val dataDomani: String = "",
    val costaDomani: Bitmap? = null,
    val lunigianaDomani: Bitmap? = null,
    val dataDopodomani: String = "",
    val costaDopodomani: Bitmap? = null,
    val lunigianaDopodomani: Bitmap? = null,
)

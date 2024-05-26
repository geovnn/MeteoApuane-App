package com.geovnn.meteoapuane.model

import android.graphics.Bitmap

data class HomeUiState(
    val error: String = "",
    val isLoading: Boolean = true,
    val imgAllerta1: Bitmap? = null,
    val imgAllerta2: Bitmap? = null,
    val imgAllerta3: Bitmap? = null,
    val imgAllerta4: Bitmap? = null,
    val imgAllerta5: Bitmap? = null,
    val imgAllerta6: Bitmap? = null,
    val txtAvviso: String = "",
    val imgUltimora1: Bitmap? = null,
    val txtUltimoraTitle1: String = "",
    val txtUltimoraBody1: String = "",
    val imgUltimora2: Bitmap? = null,
    val txtUltimoraTitle2: String = "",
    val txtUltimoraBody2: String = "",
    val txtUltimaModifia: String = ""
)

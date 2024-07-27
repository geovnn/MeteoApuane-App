package com.geovnn.meteoapuane.domain.models

import android.graphics.Bitmap

data class ConfiniPageMap(
    val sfondo: String = "",

    val temperaturaVersantePadano: String = "",
    val temperaturaAppennino: String = "",
    val temperaturaVersanteLigure: String = "",

    val ventoBassaPianura: String = "",
    val ventoAppenninoLigurePiacentino: String = "",
    val ventoPedemontana: String = "",
    val ventoCostaLigure: String = "",
    val ventoCrinale: String = "",

    val mare: String = "",

    val previsioneValTrebbia: String = "",
    val previsionePianuraPiacentina: String = "",
    val previsioneTerreVerdiane: String = "",
    val previsioneBassaParmense: String = "",
    val previsioneValNure: String = "",
    val previsioneAppenninoLigurePiacentino: String = "",
    val previsioneValTaro: String = "",
    val previsionePedemontanaParmense: String = "",
    val previsioneAltaValTaro: String = "",
    val previsioneCrinaleAppenninico: String = "",
    val previsioneSpezzinoInterno: String = "",
    val previsioneAppenninoReggiano: String = "",
    val previsioneCostaSpezzina: String = "",
    )

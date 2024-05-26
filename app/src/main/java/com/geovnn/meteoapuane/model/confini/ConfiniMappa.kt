package com.geovnn.meteoapuane.model.confini

import android.graphics.Bitmap

data class ConfiniMappa(
    val sfondo: Bitmap? = null,

    val temperaturaVersantePadano: Bitmap? = null,
    val temperaturaAppennino: Bitmap? = null,
    val temperaturaVersanteLigure: Bitmap? = null,

    val ventoBassaPianura: Bitmap? = null,
    val ventoAppenninoLigurePiacentino: Bitmap? = null,
    val ventoPedemontana: Bitmap? = null,
    val ventoCostaLigure: Bitmap? = null,
    val ventoCrinale: Bitmap? = null,

    val mare: Bitmap? = null,

    val previsioneValTrebbia: Bitmap? = null,
    val previsionePianuraPiacentina: Bitmap? = null,
    val previsioneTerreVerdiane: Bitmap? = null,
    val previsioneBassaParmense: Bitmap? = null,
    val previsioneValNure: Bitmap? = null,
    val previsioneAppenninoLigurePiacentino: Bitmap? = null,
    val previsioneValTaro: Bitmap? = null,
    val previsionePedemontanaParmense: Bitmap? = null,
    val previsioneAltaValTaro: Bitmap? = null,
    val previsioneCrinaleAppenninico: Bitmap? = null,
    val previsioneSpezzinoInterno: Bitmap? = null,
    val previsioneAppenninoReggiano: Bitmap? = null,
    val previsioneCostaSpezzina: Bitmap? = null,
    )

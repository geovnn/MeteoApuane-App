package com.geovnn.meteoapuane.domain.models

import android.graphics.Bitmap

data class ProvinciaPageMap(
    val sfondo: Bitmap? = null,
    val temperature: Bitmap? = null,
    val iconaAggiunitiva: Bitmap? = null,

    val meteoAltaLunigiana: Bitmap? = null,
    val meteoVersanteEmiliano: Bitmap? = null,
    val meteoMediaAltaLunigiana: Bitmap? = null,
    val meteoLunigianaOccidentale: Bitmap? = null,
    val meteoAppenninoVersanteToscano: Bitmap? = null,
    val meteoBassaLunigiana: Bitmap? = null,
    val meteoLunigianaSudOrientale: Bitmap? = null,
    val meteoGolfoDeiPoeti: Bitmap? = null,
    val meteoBassaValDiMagra: Bitmap? = null,
    val meteoAlpiApuane: Bitmap? = null,
    val meteoMassaCarrara: Bitmap? = null,
    val meteoAltaVersilia: Bitmap? = null,

    val ventoAppennino: Bitmap? = null,
    val ventoLunigiana: Bitmap? = null,
    val ventoAlpiApuane: Bitmap? = null,
    val ventoCosta: Bitmap? = null,

    val mareSottocosta: Bitmap? = null,
    val mareLargo: Bitmap? = null,
    )

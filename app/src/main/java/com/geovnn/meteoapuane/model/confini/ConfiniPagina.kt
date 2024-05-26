package com.geovnn.meteoapuane.model.confini

data class ConfiniPagina(
    val data : String = "",
    val testoCielo : String = "",
    val testoFenomeni : String = "",
    val testoTemperature : String = "",
    val testoVenti : String = "",
    val testoMare : String = "",
    val mappa: ConfiniMappa? = null,
)

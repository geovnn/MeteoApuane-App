package com.geovnn.meteoapuane.domain.models

data class ConfiniPageTab(
    val data : String = "",
    val testoCielo : String = "",
    val testoFenomeni : String = "",
    val testoTemperature : String = "",
    val testoVenti : String = "",
    val testoMare : String = "",
    val mappa: ConfiniPageMap? = null,
)

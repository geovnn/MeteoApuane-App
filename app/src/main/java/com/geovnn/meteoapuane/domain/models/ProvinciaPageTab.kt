package com.geovnn.meteoapuane.domain.models

data class ProvinciaPageTab(
    val data : String = "",
    val mappaMattina: ProvinciaPageMap? = null,
    val mappaSera: ProvinciaPageMap? = null,
    val testo : String = "",
    val testoTemperature : String = "",
    val testoVenti : String = "",
    val testoMare : String = "",
    val temperatureMassa : Pair<Int,Int> = Pair(0,0),
    val temperatureCarrara : Pair<Int,Int> = Pair(0,0),
    val temperatureAulla : Pair<Int,Int> = Pair(0,0),
    val temperatureFivizzano : Pair<Int,Int> = Pair(0,0),
    val temperaturePontremoli : Pair<Int,Int> = Pair(0,0),
)

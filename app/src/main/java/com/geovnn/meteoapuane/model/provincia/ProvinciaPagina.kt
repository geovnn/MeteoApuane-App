package com.geovnn.meteoapuane.model.provincia

data class ProvinciaPagina(
    val data : String = "",
    val mappaMattina: ProvinciaMappa? = null,
    val mappaSera: ProvinciaMappa? = null,
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

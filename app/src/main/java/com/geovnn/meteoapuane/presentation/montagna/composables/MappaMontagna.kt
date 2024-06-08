package com.geovnn.meteoapuane.presentation.montagna.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import com.geovnn.meteoapuane.presentation.montagna.MontagnaUiState

@Composable
fun MappaMontagna(
    modifier: Modifier = Modifier,
    uiState: MontagnaUiState
) {
    var parentSize by remember { mutableStateOf<IntSize?>(null) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.66f)
            .onGloballyPositioned {
                //here u can access the parent layout coordinate size
                parentSize = it.parentLayoutCoordinates?.size
            },
    ) {
        val multiplierDpX = with(LocalDensity.current) { ((parentSize?.width ?: 0) / 100).toDp() }
        val multiplierDpY = with(LocalDensity.current) { ((parentSize?.width ?: 0) / 166).toDp() }
        val cardSize = multiplierDpX*40
        val iconSize = multiplierDpX*10
        val multiplierSp = with(LocalDensity.current) { ((parentSize?.width ?: 0) / 100).toSp() }
        val fontSize = multiplierSp*2.8

        uiState.montagnaPage.immagineSfondo?.let { Image(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            bitmap = it.asImageBitmap(),
            contentDescription = "sfondo mappa",
            contentScale = ContentScale.Fit
        ) }

        uiState.montagnaPage.immagineOggiZeri?.let {
            uiState.montagnaPage.immagineDomaniZeri?.let { it1 ->
                uiState.montagnaPage.immagineDopodomaniZeri?.let { it2 ->
                    MapCard(
                        modifier = Modifier,
                        titolo = "ZUM ZERI",
                        altitudine = "1405 m",
                        data1 = uiState.montagnaPage.testoDataOggi,
                        icona1 = it,
                        data2 = uiState.montagnaPage.testoDataDomani,
                        icona2 = it1,
                        data3 = uiState.montagnaPage.testoDataDopodomani,
                        icona3 = it2,
                        zeroTermico = uiState.montagnaPage.testoZeroZeri,
                        altezzaNeve = uiState.montagnaPage.testoNeveZeri,
                        offsetX = multiplierDpX * 20,
                        offsetY = multiplierDpY * 20,
                        multiplierDp = multiplierDpX,
                        multiplierSp = multiplierSp
                    )
                }
            }
        }

        uiState.montagnaPage.immagineOggiCampocecina?.let {
            uiState.montagnaPage.immagineDomaniCampocecina?.let { it1 ->
                uiState.montagnaPage.immagineDopodomaniCampocecina?.let { it2 ->
                    MapCard(
                        modifier = Modifier,
                        offsetX = multiplierDpX * 82,
                        offsetY = multiplierDpY * 20,
                        titolo = "CAMPOCECINA",
                        altitudine = "1283 m",
                        data1 = uiState.montagnaPage.testoDataOggi,
                        icona1 = it,
                        data2 = uiState.montagnaPage.testoDataDomani,
                        icona2 = it1,
                        data3 = uiState.montagnaPage.testoDataDopodomani,
                        icona3 = it2,
                        zeroTermico = uiState.montagnaPage.testoZeroCampocecina,
                        altezzaNeve = uiState.montagnaPage.testoNeveCampocecina,
                        multiplierDp = multiplierDpX,
                        multiplierSp = multiplierSp
                    )
                }
            }
        }

        uiState.montagnaPage.immagineOggiPratospilla?.let {
            uiState.montagnaPage.immagineDomaniPratospilla?.let { it1 ->
                uiState.montagnaPage.immagineDopodomaniPratospilla?.let { it2 ->
                    MapCard(
                        modifier = Modifier,
                        offsetX = multiplierDpX * 20,
                        offsetY = multiplierDpY * 90,
                        titolo = "PRATOSPILLA",
                        altitudine = "1352 m",
                        data1 = uiState.montagnaPage.testoDataOggi,
                        icona1 = it,
                        data2 = uiState.montagnaPage.testoDataDomani,
                        icona2 = it1,
                        data3 = uiState.montagnaPage.testoDataDopodomani,
                        icona3 = it2,
                        zeroTermico = uiState.montagnaPage.testoZeroPratospilla,
                        altezzaNeve = uiState.montagnaPage.testoNevePratospilla,
                        multiplierDp = multiplierDpX,
                        multiplierSp = multiplierSp
                    )
                }
            }
        }




        uiState.montagnaPage.immagineOggiCerreto?.let {
            uiState.montagnaPage.immagineDomaniCerreto?.let { it1 ->
                uiState.montagnaPage.immagineDopodomaniCerreto?.let { it2 ->
                    MapCard(
                        modifier = Modifier,
                        offsetX = multiplierDpX * 82,
                        offsetY = multiplierDpY * 90,
                        titolo = "CERRETO",
                        altitudine = "1350 m",
                        data1 = uiState.montagnaPage.testoDataOggi,
                        icona1 = it,
                        data2 = uiState.montagnaPage.testoDataDomani,
                        icona2 = it1,
                        data3 = uiState.montagnaPage.testoDataDopodomani,
                        icona3 = it2,
                        zeroTermico = uiState.montagnaPage.testoZeroCerreto,
                        altezzaNeve = uiState.montagnaPage.testoNeveCerreto,
                        multiplierDp = multiplierDpX,
                        multiplierSp = multiplierSp
                    )
                }
            }
        }
    }
}


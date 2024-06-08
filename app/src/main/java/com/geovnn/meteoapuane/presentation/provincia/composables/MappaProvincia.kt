package com.geovnn.meteoapuane.presentation.provincia.composables

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
import com.geovnn.meteoapuane.domain.models.ProvinciaPageMap

@Composable
fun MappaProvincia(
    modifier: Modifier = Modifier,
    mappa: ProvinciaPageMap
) {
    var parentSize by remember { mutableStateOf<IntSize?>(null) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .onGloballyPositioned {
                //here u can access the parent layout coordinate size
                parentSize = it.parentLayoutCoordinates?.size
            },
    ) {
        val multiplierDp = with(LocalDensity.current) { ((parentSize?.width ?: 0) / 100).toDp() }
        val iconSizeMeteo = multiplierDp*14
        val iconSizeVenti = multiplierDp*11

        // Icone meteo:
        mappa.sfondo?.let { Image(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            bitmap = it.asImageBitmap(),
            contentDescription = "sfondo mappa",
            contentScale = ContentScale.Fit
        ) }
        mappa.meteoAltaLunigiana?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 32,
                offsetY = multiplierDp * 16,
                size = iconSizeMeteo)
        }
        mappa.meteoVersanteEmiliano?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 62,
                offsetY = multiplierDp * 24,
                size = iconSizeMeteo)
        }
        mappa.meteoMediaAltaLunigiana?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 38,
                offsetY = multiplierDp * 32,
                size = iconSizeMeteo)
        }
        mappa.meteoLunigianaOccidentale?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 21,
                offsetY = multiplierDp * 38,
                size = iconSizeMeteo)
        }
        mappa.meteoAppenninoVersanteToscano?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 76,
                offsetY = multiplierDp * 40,
                size = iconSizeMeteo)
        }
        mappa.meteoBassaLunigiana?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 47,
                offsetY = multiplierDp * 48,
                size = iconSizeMeteo)
        }
        mappa.meteoLunigianaSudOrientale?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 66,
                offsetY = multiplierDp * 57,
                size = iconSizeMeteo)
        }
        mappa.meteoGolfoDeiPoeti?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 25,
                offsetY = multiplierDp * 64,
                size = iconSizeMeteo)
        }
        mappa.meteoBassaValDiMagra?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 44,
                offsetY = multiplierDp * 70,
                size = iconSizeMeteo)
        }
        mappa.meteoAlpiApuane?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 84,
                offsetY = multiplierDp * 68,
                size = iconSizeMeteo)
        }
        mappa.meteoMassaCarrara?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 67,
                offsetY = multiplierDp * 78,
                size = iconSizeMeteo)
        }
        mappa.meteoAltaVersilia?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 86,
                offsetY = multiplierDp * 85,
                size = iconSizeMeteo)
        }
        // Icone venti:
        mappa.ventoAppennino?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 55,
                offsetY = multiplierDp * 12,
                size = iconSizeVenti)
        }
        mappa.ventoLunigiana?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 22,
                offsetY = multiplierDp * 51,
                size = iconSizeVenti)
        }
        mappa.ventoAlpiApuane?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 97,
                offsetY = multiplierDp * 72,
                size = iconSizeVenti)
        }
        mappa.ventoCosta?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 66,
                offsetY = multiplierDp * 91,
                size = iconSizeVenti)
        }
        // Icone mare:
        mappa.mareSottocosta?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 54,
                offsetY = multiplierDp * 84,
                size = iconSizeVenti)
        }
        mappa.mareLargo?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 40,
                offsetY = multiplierDp * 94,
                size = iconSizeVenti)
        }
        // Icona temperatura:
        mappa.temperature?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 8,
                offsetY = multiplierDp * 57,
                size = multiplierDp*20)
        }
    }
}


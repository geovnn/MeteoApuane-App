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
import com.geovnn.meteoapuane.presentation.utils.composables.ImageCoil

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
        ImageCoil(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
                url = mappa.sfondo,
                contentDescription = "sfondo mappa",
                contentScale = ContentScale.Fit
            )
        MapIcon(
            image = mappa.meteoAltaLunigiana,
            offsetX = multiplierDp * 32,
            offsetY = multiplierDp * 16,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.meteoVersanteEmiliano,
            offsetX = multiplierDp * 62,
            offsetY = multiplierDp * 24,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.meteoMediaAltaLunigiana,
            offsetX = multiplierDp * 38,
            offsetY = multiplierDp * 32,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.meteoLunigianaOccidentale,
            offsetX = multiplierDp * 21,
            offsetY = multiplierDp * 38,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.meteoAppenninoVersanteToscano,
            offsetX = multiplierDp * 76,
            offsetY = multiplierDp * 40,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.meteoBassaLunigiana,
            offsetX = multiplierDp * 47,
            offsetY = multiplierDp * 48,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.meteoLunigianaSudOrientale,
            offsetX = multiplierDp * 66,
            offsetY = multiplierDp * 57,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.meteoGolfoDeiPoeti,
            offsetX = multiplierDp * 25,
            offsetY = multiplierDp * 64,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.meteoBassaValDiMagra,
            offsetX = multiplierDp * 44,
            offsetY = multiplierDp * 70,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.meteoAlpiApuane,
            offsetX = multiplierDp * 84,
            offsetY = multiplierDp * 68,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.meteoMassaCarrara,
            offsetX = multiplierDp * 67,
            offsetY = multiplierDp * 78,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.meteoAltaVersilia,
            offsetX = multiplierDp * 86,
            offsetY = multiplierDp * 85,
            size = iconSizeMeteo)
        // Icone venti:
        MapIcon(
            image = mappa.ventoAppennino,
            offsetX = multiplierDp * 55,
            offsetY = multiplierDp * 12,
            size = iconSizeVenti)
        MapIcon(
            image = mappa.ventoLunigiana,
            offsetX = multiplierDp * 22,
            offsetY = multiplierDp * 51,
            size = iconSizeVenti)
        MapIcon(
            image = mappa.ventoAlpiApuane,
            offsetX = multiplierDp * 97,
            offsetY = multiplierDp * 72,
            size = iconSizeVenti)
        MapIcon(
            image = mappa.ventoCosta,
            offsetX = multiplierDp * 66,
            offsetY = multiplierDp * 91,
            size = iconSizeVenti)
        // Icone mare:
        MapIcon(
            image = mappa.mareSottocosta,
            offsetX = multiplierDp * 54,
            offsetY = multiplierDp * 84,
            size = iconSizeVenti)
        MapIcon(
            image = mappa.mareLargo,
            offsetX = multiplierDp * 40,
            offsetY = multiplierDp * 94,
            size = iconSizeVenti)
        // Icona temperatura:
        MapIcon(
            image = mappa.temperature,
            offsetX = multiplierDp * 8,
            offsetY = multiplierDp * 57,
            size = multiplierDp*20)
    }
}


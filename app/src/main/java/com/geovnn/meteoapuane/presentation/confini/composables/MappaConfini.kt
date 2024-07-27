package com.geovnn.meteoapuane.presentation.confini.composables

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
import com.geovnn.meteoapuane.domain.models.ConfiniPageMap
import com.geovnn.meteoapuane.presentation.provincia.composables.MapIcon
import com.geovnn.meteoapuane.presentation.utils.composables.ImageCoil

@Composable
fun MappaConfini(
    modifier: Modifier = Modifier,
    mappa: ConfiniPageMap
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
        val iconSizeMeteo = multiplierDp*20
        val iconSizeMare = multiplierDp*12
        val iconSizeVenti = multiplierDp*11
        val iconSizeTemperatura = multiplierDp*22
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
            image = mappa.previsioneValTrebbia,
            offsetX = multiplierDp * 14,
            offsetY = multiplierDp * 25,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsionePianuraPiacentina,
            offsetX = multiplierDp * 44,
            offsetY = multiplierDp * 17,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsioneTerreVerdiane,
            offsetX = multiplierDp * 66,
            offsetY = multiplierDp * 28,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsioneBassaParmense,
            offsetX = multiplierDp * 90,
            offsetY = multiplierDp * 19,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsioneValNure,
            offsetX = multiplierDp * 39,
            offsetY = multiplierDp * 37,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsioneAppenninoLigurePiacentino,
            offsetX = multiplierDp * 20,
            offsetY = multiplierDp * 47,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsioneValTaro,
            offsetX = multiplierDp * 48,
            offsetY = multiplierDp * 51,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsionePedemontanaParmense,
            offsetX = multiplierDp * 80,
            offsetY = multiplierDp * 45,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsioneAltaValTaro,
            offsetX = multiplierDp * 31,
            offsetY = multiplierDp * 63,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsioneCrinaleAppenninico,
            offsetX = multiplierDp * 66,
            offsetY = multiplierDp * 62,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsioneAppenninoReggiano,
            offsetX = multiplierDp * 93,
            offsetY = multiplierDp * 70,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsioneSpezzinoInterno,
            offsetX = multiplierDp * 19,
            offsetY = multiplierDp * 72,
            size = iconSizeMeteo)
        MapIcon(
            image = mappa.previsioneCostaSpezzina,
            offsetX = multiplierDp * 39,
            offsetY = multiplierDp * 87,
            size = iconSizeMeteo)
        // Icone venti:
        MapIcon(
            image = mappa.ventoBassaPianura,
            offsetX = multiplierDp * 59,
            offsetY = multiplierDp * 10,
            size = iconSizeVenti)
        MapIcon(
            image = mappa.ventoAppenninoLigurePiacentino,
            offsetX = multiplierDp * 21,
            offsetY = multiplierDp * 35,
            size = iconSizeVenti)
        MapIcon(
            image = mappa.ventoPedemontana,
            offsetX = multiplierDp * 65,
            offsetY = multiplierDp * 42,
            size = iconSizeVenti)
        MapIcon(
            image = mappa.ventoCostaLigure,
            offsetX = multiplierDp * 8,
            offsetY = multiplierDp * 68,
            size = iconSizeVenti)
        MapIcon(
            image = mappa.ventoCrinale,
            offsetX = multiplierDp * 80,
            offsetY = multiplierDp * 69,
            size = iconSizeVenti)
        // Icone mare:
        MapIcon(
            image = mappa.mare,
            offsetX = multiplierDp * 28,
            offsetY = multiplierDp * 93,
            size = iconSizeMare)

        // Icona temperatura:
        MapIcon(
            image = mappa.temperaturaVersantePadano,
            offsetX = multiplierDp * 16,
            offsetY = multiplierDp * 10,
            size = iconSizeTemperatura)
        MapIcon(
            image = mappa.temperaturaAppennino,
            offsetX = multiplierDp * 92,
            offsetY = multiplierDp * 55,
            size = iconSizeTemperatura)
        MapIcon(
            image = mappa.temperaturaVersanteLigure,
            offsetX = multiplierDp * 60,
            offsetY = multiplierDp * 92,
            size = iconSizeTemperatura)
    }
}

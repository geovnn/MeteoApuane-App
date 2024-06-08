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
        mappa.sfondo?.let { Image(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            bitmap = it.asImageBitmap(),
            contentDescription = "sfondo mappa",
            contentScale = ContentScale.Fit
        ) }
        mappa.previsioneValTrebbia?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 14,
                offsetY = multiplierDp * 25,
                size = iconSizeMeteo)
        }
        mappa.previsionePianuraPiacentina?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 44,
                offsetY = multiplierDp * 17,
                size = iconSizeMeteo)
        }
        mappa.previsioneTerreVerdiane?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 66,
                offsetY = multiplierDp * 28,
                size = iconSizeMeteo)
        }
        mappa.previsioneBassaParmense?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 90,
                offsetY = multiplierDp * 19,
                size = iconSizeMeteo)
        }
        mappa.previsioneValNure?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 39,
                offsetY = multiplierDp * 37,
                size = iconSizeMeteo)
        }
        mappa.previsioneAppenninoLigurePiacentino?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 20,
                offsetY = multiplierDp * 47,
                size = iconSizeMeteo)
        }
        mappa.previsioneValTaro?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 48,
                offsetY = multiplierDp * 51,
                size = iconSizeMeteo)
        }
        mappa.previsionePedemontanaParmense?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 80,
                offsetY = multiplierDp * 45,
                size = iconSizeMeteo)
        }
        mappa.previsioneAltaValTaro?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 31,
                offsetY = multiplierDp * 63,
                size = iconSizeMeteo)
        }
        mappa.previsioneCrinaleAppenninico?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 66,
                offsetY = multiplierDp * 62,
                size = iconSizeMeteo)
        }
        mappa.previsioneAppenninoReggiano?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 93,
                offsetY = multiplierDp * 70,
                size = iconSizeMeteo)
        }
        mappa.previsioneSpezzinoInterno?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 19,
                offsetY = multiplierDp * 72,
                size = iconSizeMeteo)
        }
        mappa.previsioneCostaSpezzina?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 39,
                offsetY = multiplierDp * 87,
                size = iconSizeMeteo)
        }
        // Icone venti:
        mappa.ventoBassaPianura?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 59,
                offsetY = multiplierDp * 10,
                size = iconSizeVenti)
        }
        mappa.ventoAppenninoLigurePiacentino?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 21,
                offsetY = multiplierDp * 35,
                size = iconSizeVenti)
        }
        mappa.ventoPedemontana?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 65,
                offsetY = multiplierDp * 42,
                size = iconSizeVenti)
        }
        mappa.ventoCostaLigure?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 8,
                offsetY = multiplierDp * 68,
                size = iconSizeVenti)
        }
        mappa.ventoCrinale?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 80,
                offsetY = multiplierDp * 69,
                size = iconSizeVenti)
        }
        // Icone mare:
        mappa.mare?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 28,
                offsetY = multiplierDp * 93,
                size = iconSizeMare)
        }

        // Icona temperatura:
        mappa.temperaturaVersantePadano?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 16,
                offsetY = multiplierDp * 10,
                size = iconSizeTemperatura)
        }
        mappa.temperaturaAppennino?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 92,
                offsetY = multiplierDp * 55,
                size = iconSizeTemperatura)
        }
        mappa.temperaturaVersanteLigure?.let {
            MapIcon(
                image = it,
                offsetX = multiplierDp * 60,
                offsetY = multiplierDp * 92,
                size = iconSizeTemperatura)
        }
    }
}

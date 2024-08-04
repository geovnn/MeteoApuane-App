package com.geovnn.meteoapuane.presentation.nowcasting

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextButton
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.geovnn.meteoapuane.presentation.utils.composables.ImageCoil
import com.geovnn.meteoapuane.presentation.utils.composables.TitleText
import com.geovnn.meteoapuane.presentation.webcam.WebcamState
import com.geovnn.meteoapuane.presentation.webcam.composables.WebcamImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NowcastingScreen(
    uiState: NowcastingState,
    refreshData: () -> Unit,
) {
    val refreshScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        isRefreshing = true
        refreshData()
        delay(1000)
        isRefreshing = false
    }
    LaunchedEffect(Unit) {
        refreshData()
    }
    val state = rememberPullRefreshState(isRefreshing, ::refresh)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(state)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.onPrimary,
            )
        } else if (uiState.error != "") {
            AlertDialog(
                onDismissRequest = { },
                confirmButton = {
                    TextButton(onClick = { refreshData() }) {
                        Text(text = "Riprova")
                    }
                },
                title = { Text(text = "Errore") },
                text = { Text(text = uiState.error) },
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        } else {
            var fullscreenImage by remember { mutableStateOf("") }
            var showFullscreenImage by remember { mutableStateOf(false) }

            val alpha: Float by animateFloatAsState(if (showFullscreenImage) 0f else 1f)

            Column(
                modifier = Modifier
                    .alpha(alpha)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                ElevatedCard(
                    modifier = Modifier.padding(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    TitleText(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp), text = "STRUMENTI DI NOWCASTING")
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.nowcastingPage.meteosatInfrarosso,
                            title = "METEOSAT Infrarosso (Europa)",
                            subtitle = null,
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.nowcastingPage.meteosatInfrarossoAnimazione,
                            title = "METEOSAT infrarosso animazione (Europa)",
                            subtitle = null,
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.nowcastingPage.temperaturaNubi,
                            title = "TEMPERATURA NUBI al top (Europa)",
                            subtitle = null,
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.nowcastingPage.meteosatVisibileAnimazione,
                            title = "METEOSAT visibile animazione (Italia)",
                            subtitle = null,
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.nowcastingPage.fulminazioniAnimazioneGolfoLigure,
                            title = "FULMINAZIONI animazione (Golfo Ligure)",
                            subtitle = null,
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.nowcastingPage.fulminazioniAnimazioneItalia,
                            title = "FULMINAZIONI animazione (Italia)",
                            subtitle = null,
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.nowcastingPage.radarPrecipitazioniSettepani,
                            title = "RADAR PRECIPITAZIONI \"Settepani\" (Nord-Ovest)",
                            subtitle = null,
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.nowcastingPage.radarPrecipitazioniMeteoFrance,
                            title = "RADAR PRECIPITAZIONI \"MeteoFrance\"",
                            subtitle = null,
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.nowcastingPage.cartaSinotticaEuropa,
                            title = "CARTA SINOTTICA (Europa)",
                            subtitle = null,
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.nowcastingPage.cartaSinotticaItalia,
                            title = "CARTA SINOTTICA (Italia)",
                            subtitle = null,
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                }
            }

            AnimatedVisibility(visible = showFullscreenImage,
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                val image = fullscreenImage
                if (image != "") {
                    ImageCoil(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { showFullscreenImage = false },
                        url = image,
                        contentDescription=""
                    )
                }

            }
        }
        PullRefreshIndicator(isRefreshing, state, Modifier.align(Alignment.TopCenter))
    }
}
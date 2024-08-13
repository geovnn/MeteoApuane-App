package com.geovnn.meteoapuane.presentation.webcam

import android.app.Activity
import android.graphics.Bitmap
import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.geovnn.meteoapuane.presentation.incendi.IncendiState
import com.geovnn.meteoapuane.presentation.utils.composables.ImageCoil
import com.geovnn.meteoapuane.presentation.utils.composables.TitleText
import com.geovnn.meteoapuane.presentation.utils.composables.VideoView
import com.geovnn.meteoapuane.presentation.webcam.composables.WebcamImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WebcamScreen(
    uiState: WebcamState,
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
    val activity = (LocalContext.current as? Activity)


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
            var fullscreenImage by remember { mutableStateOf<String>("") }
            var showFullscreenImage by remember { mutableStateOf(false) }

            val alpha: Float by animateFloatAsState(if (showFullscreenImage) 0f else 1f)
            BackHandler {
                if (showFullscreenImage) {
                    showFullscreenImage=false
                } else {
                    activity?.finish()
                }
            }
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
                    TitleText(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp), text = "PROVINCIA DI MASSA CARRARA")
                    WebcamImage(
                        modifier = Modifier.fillMaxWidth(),
                        image = uiState.webcamPage.massaCentro,
                        title = "MASSA Centro (80 m)",
                        subtitle = "Panoramica 180° (W-N-E) su promontorio di Montemarcello, colline del Candia, M. Brugiana e M. Tambura",
                        onClick = {
                            fullscreenImage=it
                            showFullscreenImage=true
                        }
                    )
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.moncigoli,
                            title = "MONCIGOLI (230 m)",
                            subtitle = "Alpi Apuane (M. Pisanino, Pizzo d'Uccello e Sagro)",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.canevara,
                            title = "CANEVARA (170 m)",
                            subtitle = "Alpi Apuane (M. Grondilice, Contrario e Cavallo)",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.monteBorla,
                            title = "MONTE BORLA (1470 m)",
                            subtitle = "panorama su Carrara e Marina dalla vetta",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.vinca,
                            title = "VINCA (760 m)",
                            subtitle = "vista del paese, Pizzo d'Uccello e cresta Garnerone",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.marinaDiMassa,
                            title = "MARINA DI MASSA (10 m)",
                            subtitle = "vista a Sud-Ovest sul pontile e foce Brugiano",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.marinaDiCarrara,
                            title = "MARINA DI CARRARA (5 m)",
                            subtitle = "vista sulla spiaggia direzione Sud-Ovest",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.avenza,
                            title = "AVENZA (15 m)",
                            subtitle = "vista ad Est sulle Apuane e Monte Sagro",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.fivizzano,
                            title = "FIVIZZANO (370 m)",
                            subtitle = "vista a Sud-Ovest sul paese e Valle del Rosaro",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.villafrancaLunigiana,
                            title = "VILLAFRANCA LUNIGIANA (135 m)",
                            subtitle = "vista a Nord su Monte Molinatico e S.S. della Cisa",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.bagnone,
                            title = "BAGNONE (290 m)",
                            subtitle = "vista a Est su M. Marmagna, M. Sillara e Treschietto",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.sassalbo,
                            title = "SASSALBO (1000 m)",
                            subtitle = "vista sul paese e M. Alto da Casa Giannino",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.monteBosta,
                            title = "MONTE BOSTA (870 m)",
                            subtitle = "panorama a Ovest su Val Caprio e Alta Lunigiana",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    WebcamImage(
                        modifier  = Modifier,
                        image = uiState.webcamPage.zumZeri,
                        title = "ZUM ZERI (1395 m)",
                        subtitle = "vista sul Rifugio e Passo dei Due Santi",
                        onClick = {
                            fullscreenImage=it
                            showFullscreenImage=true
                        }
                    )
                }

                ElevatedCard(
                    modifier = Modifier.padding(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    TitleText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        text = "APPENNINO REGGIANO e PARMENSE"
                    )
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.passoDelCerreto1,
                            title = "PASSO DEL CERRETO (1270 m)",
                            subtitle = "vista a Nord su Monte Casarola e S.S. 63",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.passoDelCerreto2,
                            title = "PASSO DEL CERRETO (1270 m)",
                            subtitle = "vista a Sud-Est su Valle dell'Inferno e M. La Nuda",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.cerretoLaghi,
                            title = "CERRETO LAGHI (1340 m)",
                            subtitle = "vista su Lago Cerretano e partenza impianti",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.rigoso,
                            title = "RIGOSO (1130 m)",
                            subtitle = "vista a Sud sull'Alpe di Succiso e Passo Lagastrello",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.pratospilla1,
                            title = "PRATOSPILLA (1355 m)",
                            subtitle = "vista a Ovest su partenza impianti e M. Bocco",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.pratospilla2,
                            title = "PRATOSPILLA (1355 m)",
                            subtitle = "vista a Nord-Est sul piazzale",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {

                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.monteCusna,
                            title = "MONTE CUSNA (2055 m)",
                            subtitle = "panorama verso Nord-Ovest dalla vetta",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.lagoSanto,
                            title = "LAGO SANTO (1510 m)",
                            subtitle = "vista sul lago e Rifugio Mariotti",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {

                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.valditacca,
                            title = "VALDITACCA (1010 m)",
                            subtitle = "vista a Sud verso il crinale e Monte Sillara",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.trefiumi,
                            title = "TREFIUMI (920 m)",
                            subtitle = "vista a Nord sul paese",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }
                    Row {

                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.borgoValDiTaro,
                            title = "BORGO VAL DI TARO (450 m)",
                            subtitle = "vista a Sud-Est su fondovalle Taro e M. Molinatico",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                        WebcamImage(
                            modifier  = Modifier.weight(1f),
                            image = uiState.webcamPage.ghiareDiBerceto,
                            title = "GHIARE DI BERCETO (305 m)",
                            subtitle = "vista a Sud-Ovest sul fondovalle del Taro",
                            onClick = {
                                fullscreenImage=it
                                showFullscreenImage=true
                            }
                        )
                    }

                }

                ElevatedCard(
                    modifier = Modifier.padding(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    TitleText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        text = "PROVINCIA della SPEZIA"
                    )
                    Row {
                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.ponzanoMagra,
                            title = "PONZANO MAGRA (335 m)",
                            subtitle = "panorama sulla piana di Santo Stefano e Vezzano",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )
                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.boccaDiMagra,
                            title = "BOCCA DI MAGRA (10 m)",
                            subtitle = "vista a Est su Marina di Carrara e Alpi Apuane",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.lerici,
                            title = "LERICI (210 m)",
                            subtitle = "Isola Palmaria, Portovenere e Golfo della Spezia",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )
                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.portovenere,
                            title = "PORTOVENERE (15 m)",
                            subtitle = "vista a Sud-Est verso il Golfo e le Alpi Apuane",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.sestaGodano,
                            title = "SESTA GODANO (585 m)",
                            subtitle = "vista ad Ovest sul paese e Bracco da Godano",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )
                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.vareseLigure,
                            title = "VARESE LIGURE (540 m)",
                            subtitle = "vista a Nord-Est sul paese e Passo Cento Croci",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )

                    }
                }

                ElevatedCard(
                    modifier = Modifier.padding(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    TitleText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        text = "VERSILIA e GARFAGNANA"
                    )
                    Row {
                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.pietrasanta,
                            title = "PIETRASANTA (20 m)",
                            subtitle = "vista a Est sulla piazza del Duomo",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )
                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.lidoDiCamaiore1,
                            title = "LIDO DI CAMAIORE (15 m)",
                            subtitle = "vista a Nord su Apuane (Corchia, Pania, Gabberi)",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )
                    }
                    Row {
                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.lidoDiCamaiore2,
                            title = "LIDO DI CAMAIORE (10 m)",
                            subtitle = "vista a Ovest sulla spiaggia e pontile",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )
                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.viareggio,
                            title = "VIAREGGIO (10 m)",
                            subtitle = "vista a Est su M. Matanna e Apuane meridionali",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )
                    }
                    Row {

                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.capanneDiCareggine,
                            title = "CAPANNE DI CAREGGINE (785 m)",
                            subtitle = "vista sul lago di Isola Santa e Pizzo delle Saette",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )
                        WebcamImage(
                            modifier = Modifier.weight(1f),
                            image = uiState.webcamPage.monteArgegna,
                            title = "MONTE ARGEGNA (1010 m)",
                            subtitle = "vista a Nord su Santuario di N. S. della Guardia",
                            onClick = {
                                fullscreenImage = it
                                showFullscreenImage = true
                            }
                        )

                    }
                }
            }


            AnimatedVisibility(visible = showFullscreenImage,
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                println(fullscreenImage)
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

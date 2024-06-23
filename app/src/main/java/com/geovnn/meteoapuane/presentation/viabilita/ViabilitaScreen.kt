package com.geovnn.meteoapuane.presentation.viabilita

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.presentation.home.composables.WebcamText
import com.geovnn.meteoapuane.presentation.utils.FontSizeRange
import com.geovnn.meteoapuane.presentation.utils.composables.AutoResizeText
import com.geovnn.meteoapuane.presentation.utils.composables.BodyText
import com.geovnn.meteoapuane.presentation.utils.composables.TitleText
import com.geovnn.meteoapuane.presentation.utils.composables.VideoView
import com.geovnn.meteoapuane.presentation.viabilita.composables.SegnalazioneItemRow
import com.geovnn.meteoapuane.presentation.viabilita.composables.SegnalazioneItemText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ViabilitaScreen(
    uiState: ViabilitaUiState,
    refreshData: () -> Unit
) {
    val refreshScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        isRefreshing = true
        refreshData()
        delay(1000)
        isRefreshing = false
    }
    val state = rememberPullRefreshState(isRefreshing, ::refresh)

//    LaunchedEffect(Unit) {
//        refreshData()
//    }

    var fullscreenUri by remember { mutableStateOf<String?>(null) }

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
        } else if (uiState.error!="") {
            AlertDialog(
                onDismissRequest = {  },
                confirmButton = { TextButton(onClick = { refreshData() }) {
                    Text(text = "Riprova")
                } },
                title = { Text(text = "Errore") },
                text = { Text(text = uiState.error) }
            )
        } else {
            Column(
                modifier= Modifier
                    .verticalScroll(rememberScrollState())
                    .alpha(if (fullscreenUri != null) 0f else 100f)
            ) {

                ElevatedCard(
                    modifier = Modifier.padding(5.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    TitleText(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        text = uiState.viabilitaPage.testoSegnalazione)

                }

                ElevatedCard(
                    modifier = Modifier.padding(5.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        TitleText(
                            modifier = Modifier.fillMaxWidth(),
                            text = "A15 PARMA - LA SPEZIA"
                        )
                        Row(
                            modifier = Modifier
                        ) {
                            Spacer(modifier = Modifier.weight(2f))
//                            AutoResizeText(
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center,
//                                maxLines = 1,
//                                text = "VENTO",
//                                fontSizeRange = FontSizeRange(5.sp, 20.sp)
//                            )
//                            AutoResizeText(
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center,
//                                maxLines = 1,
//                                text = "PIOGGIA",
//                                fontSizeRange = FontSizeRange(5.sp, 20.sp)
//                            )
//                            AutoResizeText(
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center,
//                                maxLines = 1,
//                                text = "NEBBIA",
//                                fontSizeRange = FontSizeRange(5.sp, 20.sp)
//                            )
//                            AutoResizeText(
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center,
//                                maxLines = 1,
//                                text = "NEVE",
//                                fontSizeRange = FontSizeRange(5.sp, 20.sp)
//                            )
//                            AutoResizeText(
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center,
//                                maxLines = 1,
//                                text = "GHIACCIO",
//                                fontSizeRange = FontSizeRange(5.sp, 20.sp)
//                            )
                            SegnalazioneItemText(modifier = Modifier.weight(1f), text = "VENTO")
                            SegnalazioneItemText(modifier = Modifier.weight(1f), text = "PIOGGIA")
                            SegnalazioneItemText(modifier = Modifier.weight(1f), text = "NEBBIA")
                            SegnalazioneItemText(modifier = Modifier.weight(1f), text = "NEVE")
                            SegnalazioneItemText(modifier = Modifier.weight(1f), text = "GHIACCIO")
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                        SegnalazioneItemRow(
                            title = "La Spezia - S. Stefano M.",
                            img1 = uiState.viabilitaPage.imgA15LaspeziaVento,
                            img2 = uiState.viabilitaPage.imgA15LaspeziaPioggia,
                            img3 = uiState.viabilitaPage.imgA15LaspeziaNebbia,
                            img4 = uiState.viabilitaPage.imgA15LaspeziaNeve,
                            img5 = uiState.viabilitaPage.imgA15LaspeziaGhiaccio
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                        SegnalazioneItemRow(
                            title = "S. Stefano M. - Aulla",
                            img1 = uiState.viabilitaPage.imgA15SantostefanoVento,
                            img2 = uiState.viabilitaPage.imgA15SantostefanoPioggia,
                            img3 = uiState.viabilitaPage.imgA15SantostefanoNebbia,
                            img4 = uiState.viabilitaPage.imgA15SantostefanoNeve,
                            img5 = uiState.viabilitaPage.imgA15SantostefanoGhiaccio
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                        SegnalazioneItemRow(
                            title = "Aulla - Pontremoli",
                            img1 = uiState.viabilitaPage.imgA15AullaVento,
                            img2 = uiState.viabilitaPage.imgA15AullaPioggia,
                            img3 = uiState.viabilitaPage.imgA15AullaNebbia,
                            img4 = uiState.viabilitaPage.imgA15AullaNeve,
                            img5 = uiState.viabilitaPage.imgA15AullaGhiaccio
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                        SegnalazioneItemRow(
                            title = "Pontremoli - Berceto",
                            img1 = uiState.viabilitaPage.imgA15PontremoliVento,
                            img2 = uiState.viabilitaPage.imgA15PontremoliPioggia,
                            img3 = uiState.viabilitaPage.imgA15PontremoliNebbia,
                            img4 = uiState.viabilitaPage.imgA15PontremoliNeve,
                            img5 = uiState.viabilitaPage.imgA15PontremoliGhiaccio
                        )













                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                        Row(
                            modifier = Modifier.height(100.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                AutoResizeText(
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    text = "S. Stefano Magra - km 100+100",
                                    fontSizeRange = FontSizeRange(5.sp, 20.sp)
                                )
//                                WebcamText(text = "Ceparana - km 90+400")
                                Box() {
                                    if (uiState.viabilitaPage.urlVideoA15SantoStefano!="") {
                                        VideoView(
                                            videoUri = uiState.viabilitaPage.urlVideoA15SantoStefano,
                                            onClick = {
                                                fullscreenUri=it
                                            }
                                        )
                                    } else {
                                        Icon(imageVector = Icons.Default.BrokenImage, contentDescription = null)
                                    }
                                }

                            }
                            VerticalDivider(modifier = Modifier.padding(horizontal = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                            Column(modifier = Modifier.weight(1f)) {
                                AutoResizeText(
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    text = "Pontremoli - km 73+600",
                                    fontSizeRange = FontSizeRange(5.sp, 20.sp)
                                )
//                                WebcamText(text = "Luni - km 106+800")
                                Box() {
                                    if (uiState.viabilitaPage.urlVideoA15Pontremoli!="") {
                                        VideoView(
                                            videoUri = uiState.viabilitaPage.urlVideoA15Pontremoli,
                                            onClick = {
                                                fullscreenUri=it
                                            }
                                        )
                                    } else {
                                        Icon(imageVector = Icons.Default.BrokenImage, contentDescription = null)
                                    }
                                }
                            }

                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Row(
                            modifier = Modifier.height(100.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                AutoResizeText(
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    text = "Montelungo - km 60+600",
                                    fontSizeRange = FontSizeRange(5.sp, 20.sp)
                                )
//                                WebcamText(text = "Avenza - km 114+000")
                                Box {
                                    if (uiState.viabilitaPage.urlVideoA15Montelungo != "") {
                                        VideoView(
                                            videoUri = uiState.viabilitaPage.urlVideoA15Montelungo,
                                            onClick = {
                                                fullscreenUri = it
                                            }
                                        )
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.BrokenImage,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                            VerticalDivider(
                                modifier = Modifier.padding(horizontal = 2.dp),
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1f)
//                                .border(
//                                    width = 1.dp,
//                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
//                                    shape = RoundedCornerShape(10)
//                                )
                            ) {
                                AutoResizeText(
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    text = "Tugo - km 53+000",
                                    fontSizeRange = FontSizeRange(5.sp, 20.sp)
                                )
//                                WebcamText(text = "Cinquale - km 123+700")
                                Box {
                                    if (uiState.viabilitaPage.urlVideoA15Tugo != "") {
                                        VideoView(
                                            videoUri = uiState.viabilitaPage.urlVideoA15Tugo,
                                            onClick = {
                                                fullscreenUri = it
                                            }
                                        )
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.BrokenImage,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                ElevatedCard(
                    modifier = Modifier.padding(5.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        TitleText(
                            modifier = Modifier.fillMaxWidth(),
                            text = "A12 GENOVA - ROSIGNANO M."
                        )
                        Row(
                            modifier = Modifier
                        ) {
                            Spacer(modifier = Modifier.weight(2f))
                            SegnalazioneItemText(modifier = Modifier.weight(1f), text = "VENTO")
                            SegnalazioneItemText(modifier = Modifier.weight(1f), text = "PIOGGIA")
                            SegnalazioneItemText(modifier = Modifier.weight(1f), text = "NEBBIA")
                            SegnalazioneItemText(modifier = Modifier.weight(1f), text = "NEVE")
                            SegnalazioneItemText(modifier = Modifier.weight(1f), text = "GHIACCIO")
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                        SegnalazioneItemRow(
                            title = "Versilia - Massa",
                            img1 = uiState.viabilitaPage.imgA12VersiliaVento,
                            img2 = uiState.viabilitaPage.imgA12VersiliaPioggia,
                            img3 = uiState.viabilitaPage.imgA12VersiliaNebbia,
                            img4 = uiState.viabilitaPage.imgA12VersiliaNeve,
                            img5 = uiState.viabilitaPage.imgA12VersiliaGhiaccio
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                        SegnalazioneItemRow(
                            title = "Massa - Carrara",
                            img1 = uiState.viabilitaPage.imgA12MassaVento,
                            img2 = uiState.viabilitaPage.imgA12MassaPioggia,
                            img3 = uiState.viabilitaPage.imgA12MassaNebbia,
                            img4 = uiState.viabilitaPage.imgA12MassaNeve,
                            img5 = uiState.viabilitaPage.imgA12MassaGhiaccio
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                        SegnalazioneItemRow(
                            title = "Carrara - Sarzana",
                            img1 = uiState.viabilitaPage.imgA12CarraraVento,
                            img2 = uiState.viabilitaPage.imgA12CarraraPioggia,
                            img3 = uiState.viabilitaPage.imgA12CarraraNebbia,
                            img4 = uiState.viabilitaPage.imgA12CarraraNeve,
                            img5 = uiState.viabilitaPage.imgA12CarraraGhiaccio
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                        SegnalazioneItemRow(
                            title = "Sarzana - S. Stefano M.",
                            img1 = uiState.viabilitaPage.imgA12SarzanaVento,
                            img2 = uiState.viabilitaPage.imgA12SarzanaPioggia,
                            img3 = uiState.viabilitaPage.imgA12SarzanaNebbia,
                            img4 = uiState.viabilitaPage.imgA12SarzanaNeve,
                            img5 = uiState.viabilitaPage.imgA12SarzanaGhiaccio
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                        Row(
                            modifier = Modifier.height(100.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                AutoResizeText(
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    text = "Ceparana - km 90+400",
                                    fontSizeRange = FontSizeRange(5.sp, 20.sp)
                                )
//                                WebcamText(text = "Ceparana - km 90+400")
                                Box() {
                                    if (uiState.viabilitaPage.urlVideoA12Ceparana!="") {
                                        VideoView(
                                            videoUri = uiState.viabilitaPage.urlVideoA12Ceparana,
                                            onClick = {
                                                fullscreenUri=it
                                            }
                                        )
                                    } else {
                                        Icon(imageVector = Icons.Default.BrokenImage, contentDescription = null)
                                    }
                                }

                            }
                            VerticalDivider(modifier = Modifier.padding(horizontal = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

                            Column(modifier = Modifier.weight(1f)) {
                                AutoResizeText(
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    text = "Luni - km 106+800",
                                    fontSizeRange = FontSizeRange(5.sp, 20.sp)
                                )
//                                WebcamText(text = "Luni - km 106+800")
                                Box() {
                                    if (uiState.viabilitaPage.urlVideoA12Luni!="") {
                                        VideoView(
                                            videoUri = uiState.viabilitaPage.urlVideoA12Luni,
                                            onClick = {
                                                fullscreenUri=it
                                            }
                                        )
                                    } else {
                                        Icon(imageVector = Icons.Default.BrokenImage, contentDescription = null)
                                    }
                                }
                            }

                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Row(
                            modifier = Modifier.height(100.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                AutoResizeText(
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    text = "Avenza - km 114+000",
                                    fontSizeRange = FontSizeRange(5.sp, 20.sp)
                                )
//                                WebcamText(text = "Avenza - km 114+000")
                                Box{
                                    if (uiState.viabilitaPage.urlVideoA12Avenza!="") {
                                        VideoView(
                                            videoUri = uiState.viabilitaPage.urlVideoA12Avenza,
                                            onClick = {
                                                fullscreenUri=it
                                            }
                                        )
                                    } else {
                                        Icon(imageVector = Icons.Default.BrokenImage, contentDescription = null)
                                    }
                                }
                            }
                            VerticalDivider(modifier = Modifier.padding(horizontal = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)
                            Column(modifier = Modifier
                                .weight(1f)
//                                .border(
//                                    width = 1.dp,
//                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
//                                    shape = RoundedCornerShape(10)
//                                )
                            ) {
                                AutoResizeText(
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    text = "Cinquale - km 123+700",
                                    fontSizeRange = FontSizeRange(5.sp, 20.sp)
                                )
//                                WebcamText(text = "Cinquale - km 123+700")
                                Box {
                                    if (uiState.viabilitaPage.urlVideoA12Cinquale!="") {
                                        VideoView(
                                            videoUri = uiState.viabilitaPage.urlVideoA12Cinquale,
                                            onClick = {
                                                fullscreenUri=it
                                            }
                                        )
                                    } else {
                                        Icon(imageVector = Icons.Default.BrokenImage, contentDescription = null)
                                    }
                                }
                            }

                        }

                    }
                }

            }
            AnimatedVisibility(visible = fullscreenUri!=null,
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                VideoView(
                    videoUri = fullscreenUri.toString(),
                    onClick = {
                        fullscreenUri=null
                    }
                )
            }

        }
        PullRefreshIndicator(isRefreshing, state, Modifier.align(Alignment.TopCenter))

    }
}
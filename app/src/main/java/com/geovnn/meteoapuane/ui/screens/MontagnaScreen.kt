package com.geovnn.meteoapuane.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.widget.TextViewCompat.AutoSizeTextType
import com.geovnn.meteoapuane.AutoResizeText
import com.geovnn.meteoapuane.FontSizeRange
import com.geovnn.meteoapuane.model.MontagnaUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MontagnaScreen(
    uiState: MontagnaUiState,
    onMenuClick: () -> Unit,
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(text = "Montagna",
                    color = MaterialTheme.colorScheme.onPrimaryContainer) },
                navigationIcon = {
                    IconButton(onClick = { onMenuClick() }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },

        ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .pullRefresh(state)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
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
                    modifier=Modifier.verticalScroll(rememberScrollState())
                ) {
//                    Text(
//                        text = uiState.testoUltimoAggiornamento,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(3.dp),
//                        fontSize = 14.sp,
//
//                        textAlign = TextAlign.Center,
//                    )

                    AutoResizeText(
                        text = uiState.testoUltimoAggiornamento,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 2.dp),
                        fontSizeRange = FontSizeRange(
                            min = 10.sp,
                            max = 22.sp,
                        ),
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium,
                    )

//                    Divider()

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "BOLLETTINO MONTAGNA",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Previsioni a 3 giorni per le principali località sciistiche e montane delle Apuane ed Appennino settentrionale ",
                        fontWeight = FontWeight.Bold,
//                                fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )

                    MappaMontagna(uiState = uiState)
                    ElevatedCard(
                        modifier = Modifier.padding(5.dp),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(5.dp)) {


                            Text(
                                text = uiState.testo,
                                modifier = Modifier
                                    .fillMaxWidth()
//                                    .padding(3.dp),
//                        fontSize = 14.sp,
                            )
                        }
                    }


                }
            }
        }
    }
}

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

        uiState.immagineSfondo?.let { Image(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            bitmap = it.asImageBitmap(),
            contentDescription = "sfondo mappa",
            contentScale = ContentScale.Fit
        ) }

        uiState.immagineOggiZeri?.let {
            uiState.immagineDomaniZeri?.let { it1 ->
                uiState.immagineDopodomaniZeri?.let { it2 ->
                    MapCard(
                        modifier = Modifier,
                        titolo = "ZUM ZERI",
                        altitudine = "1405 m",
                        data1 = uiState.testoDataOggi,
                        icona1 = it,
                        data2 = uiState.testoDataDomani,
                        icona2 = it1,
                        data3 = uiState.testoDataDopodomani,
                        icona3 = it2,
                        zeroTermico = uiState.testoZeroZeri,
                        altezzaNeve = uiState.testoNeveZeri,
                        offsetX = multiplierDpX * 20,
                        offsetY = multiplierDpY * 20,
                        multiplierDp = multiplierDpX,
                        multiplierSp = multiplierSp
                    )
                }
            }
        }

        uiState.immagineOggiCampocecina?.let {
            uiState.immagineDomaniCampocecina?.let { it1 ->
                uiState.immagineDopodomaniCampocecina?.let { it2 ->
                    MapCard(
                        modifier = Modifier,
                        offsetX = multiplierDpX * 82,
                        offsetY = multiplierDpY * 20,
                        titolo = "CAMPOCECINA",
                        altitudine = "1283 m",
                        data1 = uiState.testoDataOggi,
                        icona1 = it,
                        data2 = uiState.testoDataDomani,
                        icona2 = it1,
                        data3 = uiState.testoDataDopodomani,
                        icona3 = it2,
                        zeroTermico = uiState.testoZeroCampocecina,
                        altezzaNeve = uiState.testoNeveCampocecina,
                        multiplierDp = multiplierDpX,
                        multiplierSp = multiplierSp
                    )
                }
            }
        }

        uiState.immagineOggiPratospilla?.let {
            uiState.immagineDomaniPratospilla?.let { it1 ->
                uiState.immagineDopodomaniPratospilla?.let { it2 ->
                    MapCard(
                        modifier = Modifier,
                        offsetX = multiplierDpX * 20,
                        offsetY = multiplierDpY * 90,
                        titolo = "PRATOSPILLA",
                        altitudine = "1352 m",
                        data1 = uiState.testoDataOggi,
                        icona1 = it,
                        data2 = uiState.testoDataDomani,
                        icona2 = it1,
                        data3 = uiState.testoDataDopodomani,
                        icona3 = it2,
                        zeroTermico = uiState.testoZeroPratospilla,
                        altezzaNeve = uiState.testoNevePratospilla,
                        multiplierDp = multiplierDpX,
                        multiplierSp = multiplierSp
                    )
                }
            }
        }




        uiState.immagineOggiCerreto?.let {
            uiState.immagineDomaniCerreto?.let { it1 ->
                uiState.immagineDopodomaniCerreto?.let { it2 ->
                    MapCard(
                        modifier = Modifier,
                        offsetX = multiplierDpX * 82,
                        offsetY = multiplierDpY * 90,
                        titolo = "CERRETO",
                        altitudine = "1350 m",
                        data1 = uiState.testoDataOggi,
                        icona1 = it,
                        data2 = uiState.testoDataDomani,
                        icona2 = it1,
                        data3 = uiState.testoDataDopodomani,
                        icona3 = it2,
                        zeroTermico = uiState.testoZeroCerreto,
                        altezzaNeve = uiState.testoNeveCerreto,
                        multiplierDp = multiplierDpX,
                        multiplierSp = multiplierSp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapCard(
    modifier: Modifier = Modifier,
    multiplierDp: Dp,
    multiplierSp: TextUnit,
    offsetX: Dp,
    offsetY: Dp,
    titolo: String,
    altitudine: String,
    data1: String,
    icona1: Bitmap,
    data2: String,
    icona2: Bitmap,
    data3: String,
    icona3: Bitmap,
    zeroTermico: String,
    altezzaNeve: String,
    ) {
    var parentSize by remember { mutableStateOf<IntSize?>(null) }
    val cardSize = multiplierDp*40
    val iconSize = multiplierDp*10
    val fontSizeTitolo = multiplierSp*2.8
    val fontSizeData = multiplierSp*2.5
    val fontSizeZeroAltezza = multiplierSp*2
    Box(
        modifier = Modifier
            .size(cardSize)
            .aspectRatio(1.91F)
            .absoluteOffset(offsetX - cardSize / 2, offsetY - cardSize / 2)
            .onGloballyPositioned {
                //here u can access the parent layout coordinate size
                parentSize = it.parentLayoutCoordinates?.size
            }
    ) {
//        val multiplierDp = with(LocalDensity.current) { ((parentSize?.width ?: 0) / 100).toDp() }
        Card(
            modifier = modifier
                .padding(1.dp)
//                .border(0.5.dp,MaterialTheme.colorScheme.outline,RoundedCornerShape(5))
                .fillMaxSize(),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(5)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize(),
            ) {
                Text(
                    modifier= Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    text = "$titolo - $altitudine",
                    fontSize = fontSizeTitolo,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier= Modifier
                        .weight(3f)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier=Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = data1, fontSize = fontSizeData)
                        Image(
                            modifier = Modifier
                                .size(iconSize)
                                .aspectRatio(1f),
                            bitmap = icona1.asImageBitmap(), contentDescription = "")
                    }
                    Column(
                        modifier=Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = data2, fontSize = fontSizeData)
                        Image(
                            modifier = Modifier
                                .size(iconSize)
                                .aspectRatio(1f),
                            bitmap = icona2.asImageBitmap(), contentDescription = "")
                    }
                    Column(
                        modifier=Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = data3, fontSize = fontSizeData)
                        Image(
                            modifier = Modifier
                                .size(iconSize)
                                .aspectRatio(1f),
                            bitmap = icona3.asImageBitmap(), contentDescription = "")
                    }
                }
//                Text(
//                    modifier= Modifier
//                        .weight(1f)
//                        .fillMaxSize(),
//                    text = "Zero Termico $zeroTermico Altezza Neve $altezzaNeve",
//                    fontSize = fontSizeZeroAltezza,
//                    textAlign = TextAlign.Center
//                )
                AutoResizeText(
//                    text = "Zero Termico $zeroTermico Altezza Neve $altezzaNeve",
                    text = "Zero Termico: $zeroTermico | Altezza Neve: $altezzaNeve",
                    maxLines = 1,
                    modifier = Modifier.weight(1f).fillMaxSize(),
                    fontSizeRange = FontSizeRange(
                        min = 1.sp,
                        max = 100.sp,
                    ),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

        }
    }


}
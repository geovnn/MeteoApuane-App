package com.geovnn.meteoapuane.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.model.provincia.ProvinciaMappa
import com.geovnn.meteoapuane.model.provincia.ProvinciaPagina
import com.geovnn.meteoapuane.model.provincia.ProvinciaPaginaSuccessivi
import com.geovnn.meteoapuane.model.provincia.ProvinciaUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun ProvinciaScreen(
    uiState: ProvinciaUiState,
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
    val coroutineScope = rememberCoroutineScope()//will use for animation

//    LaunchedEffect(Unit) {
//        refreshData()
//    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(text = "Provincia",
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
                Column {
                    val pagerState = rememberPagerState(pageCount = { 4 })
                    val tabRowItems = listOf(
                        uiState.paginaOggi.data,
                        uiState.paginaDomani.data,
                        uiState.paginaDopodomani.data,
                        uiState.paginaSuccessivi.label
                    )
                    Text(
                        text = uiState.testoUltimoAggiornamento,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    TabRow(selectedTabIndex = pagerState.currentPage) {
                        tabRowItems.forEachIndexed { index, item ->//iterate over TabItem List
                            Tab(//Create tab for each item
                                text = { Text(text = item)},
                                selected = pagerState.currentPage == index,//select only when current index is stored page
                                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } }//animate scroll onClick
                            )
                        }
                    }

                    HorizontalPager(
                        modifier = Modifier.fillMaxSize(),
                        state = pagerState,
                        beyondBoundsPageCount = 3
                    ) { page ->
                        when(page) {
                            0 -> PaginaProvincia( uiState = uiState.paginaOggi)
                            1 -> PaginaProvincia(uiState = uiState.paginaDomani)
                            2 -> PaginaProvincia(uiState = uiState.paginaDopodomani)
                            3 -> PaginaSuccessivi(uiState = uiState.paginaSuccessivi)
                        }
//                        Column(
//                            modifier = Modifier
//                                .verticalScroll(rememberScrollState())
//                                .fillMaxSize()
//                        ) {
//
//
//
//                        }

                    }

                }

            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaginaProvincia(
    modifier : Modifier = Modifier,
    uiState: ProvinciaPagina
) {
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        val pagerState = rememberPagerState(pageCount = { 2 })
        val coroutineScope = rememberCoroutineScope()
        TabRow(selectedTabIndex = pagerState.currentPage) {
            Tab(
                text = { Text(text = "Mattina")},
                selected = pagerState.currentPage == 0,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } }
            )
            Tab(
                text = { Text(text = "Sera")},
                selected = pagerState.currentPage == 1,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } }
            )
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            beyondBoundsPageCount = 1
            ) { page ->
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                when(page) {
                    0 -> {
                        if (uiState.mappaMattina!=null) {
                            MappaProvincia(mappa = uiState.mappaMattina)
                        }
                    }
                    1 -> {
                        if (uiState.mappaSera!=null) {
                            MappaProvincia(mappa = uiState.mappaSera)
                        }
                    }
                }
            }
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(modifier = Modifier.padding(5.dp),text = uiState.testo)
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(modifier = Modifier.fillMaxWidth(), text = "TEMPERATURE", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Text(text = uiState.testoTemperature)
                Divider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.secondary)
                Text(modifier = Modifier.fillMaxWidth(), text = "VENTI", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Text(text = uiState.testoVenti)
                Divider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.secondary)
                Text(modifier = Modifier.fillMaxWidth(), text = "MARE", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Text(text = uiState.testoMare)
            }
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(modifier = Modifier.fillMaxWidth(), text = "TEMPERATURE (°C)", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Row {
                    Text(modifier = Modifier.weight(4f),text = "")
                    Text(modifier = Modifier.weight(1f),text = "MIN")
                    Text(modifier = Modifier.weight(1f),text = "MAX")
                }
                Divider(color = MaterialTheme.colorScheme.secondary)
                Row {
                    Text(modifier = Modifier.weight(4f),text = "MASSA")
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureMassa.first.toString())
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureMassa.second.toString())
                }
                Divider(color = MaterialTheme.colorScheme.secondary)
                Row {
                    Text(modifier = Modifier.weight(4f),text = "CARRARA")
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureCarrara.first.toString())
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureCarrara.second.toString())
                }
                Divider(color = MaterialTheme.colorScheme.secondary)
                Row {
                    Text(modifier = Modifier.weight(4f),text = "AULLA")
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureAulla.first.toString())
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureAulla.second.toString())
                }
                Divider(color = MaterialTheme.colorScheme.secondary)
//                Row {
//                    Text(modifier = Modifier.weight(4f),text = "FIVIZZANO")
//                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureFivizzano.first.toString())
//                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureFivizzano.second.toString())
//                }
//                Divider(color = MaterialTheme.colorScheme.secondary)
                Row {
                    Text(modifier = Modifier.weight(4f),text = "PONTREMOLI")
                    Text(modifier = Modifier.weight(1f),text = uiState.temperaturePontremoli.first.toString())
                    Text(modifier = Modifier.weight(1f),text = uiState.temperaturePontremoli.second.toString())
                }
            }
        }
    }
}

@Composable
fun PaginaSuccessivi(
    modifier : Modifier = Modifier,
    uiState: ProvinciaPaginaSuccessivi
) {
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(modifier = Modifier.padding(5.dp),text = uiState.testo)
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(modifier = Modifier.fillMaxWidth(), text = uiState.data1, fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "COSTA - FONDOVALLE LUNIGIANA:",
                    )
                    uiState.imgA1?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                        ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "APUANE - APPENNINO:",
                    )
                    uiState.imgB1?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "TEMPERATURE:",
                    )
                    uiState.imgC1?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "ATTENDIBILITA:",
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = uiState.attendibilita1,
                    )
                }
            }
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(modifier = Modifier.fillMaxWidth(), text = uiState.data2, fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "COSTA - FONDOVALLE LUNIGIANA:",
                    )
                    uiState.imgA2?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "APUANE - APPENNINO:",
                    )
                    uiState.imgB2?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "TEMPERATURE:",
                    )
                    uiState.imgC2?.let { Image(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    ) }
                }
                Row(
                    modifier = Modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(5f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = "ATTENDIBILITA:",
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
//                            .fillMaxSize()
                            .align(Alignment.CenterVertically),
                        text = uiState.attendibilita2,
                    )
                }
            }
        }
    }
}

@Composable
fun MappaProvincia(
    modifier: Modifier = Modifier,
    mappa: ProvinciaMappa
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

@Composable
fun MapIcon(
    modifier: Modifier = Modifier,
    image: Bitmap,
    offsetX: Dp,
    offsetY: Dp,
    size: Dp,
) {

    Image(
        modifier = modifier
            .size(size)
            .aspectRatio(1f)
            .absoluteOffset(offsetX - size / 2, offsetY - size / 2),
        bitmap = image.asImageBitmap(),
        contentDescription = ""
    )
}
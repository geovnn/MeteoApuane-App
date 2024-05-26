package com.geovnn.meteoapuane.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.model.confini.ConfiniMappa
import com.geovnn.meteoapuane.model.confini.ConfiniPagina
import com.geovnn.meteoapuane.model.confini.ConfiniUiState
import com.geovnn.meteoapuane.model.provincia.ProvinciaMappa
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun ConfiniScreen(
    uiState: ConfiniUiState,
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
                title = { Text(text = "Confini",
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
                    )
                    Text(
                        text = uiState.testoUltimoAggiornamento,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = uiState.testoPrevisione,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
//                        fontSize = 14.sp,
                    )
                    TabRow(selectedTabIndex = pagerState.currentPage) {
                        tabRowItems.forEachIndexed { index, item ->//iterate over TabItem List
                            Tab(//Create tab for each item
                                text = { Text(text = item) },
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
                            0 -> PaginaConfini( uiState = uiState.paginaOggi)
                            1 -> PaginaConfini(uiState = uiState.paginaDomani)
                            2 -> PaginaConfini(uiState = uiState.paginaDopodomani)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PaginaConfini(
    modifier : Modifier = Modifier,
    uiState: ConfiniPagina
) {
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        if (uiState.mappa!=null) {
            MappaConfini(mappa = uiState.mappa)
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(modifier = Modifier.fillMaxWidth(), text = "CIELO", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Text(text = uiState.testoCielo)
                Divider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.secondary)
                Text(modifier = Modifier.fillMaxWidth(), text = "FENOMENI", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Text(text = uiState.testoFenomeni)
                Divider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.secondary)
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
    }
}

@Composable
fun MappaConfini(
    modifier: Modifier = Modifier,
    mappa: ConfiniMappa
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
